package com.project.user.service;

import com.project.exception.CustomException.EmailAlreadyExistsException;
import com.project.security.TokenProvider;
import com.project.user.dao.PetMapper;
import com.project.user.dao.UserMapper;
import com.project.user.dto.SignInResponseDto;
import com.project.user.dto.SignUpRequest;
import com.project.user.dto.UserDto;
import com.project.user.dto.UserEnumClass.UserStatus;
import com.project.user.dto.UserPetDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final PetMapper petMapper;
    private final TokenProvider tokenProvider;

    // BCryptPasswordEncoder 는 Spring Security 에서 제공하는 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;


    /*
        회원가입 메서드
        탈퇴한 회원은 사용했던 계정으로 재가입이 불가능하도록 설정
     */
    @Override
    public void signUp(SignUpRequest signUpRequest) {

        //이메일 중복 체크
        int isEmailExist = userMapper.checkEmailDuplication(signUpRequest.getUserEmail());

        if(isEmailExist > 0 ){
            UserDto existingUser = userMapper.findByEmail(signUpRequest.getUserEmail());

            if(existingUser.getUserStatus() == UserStatus.INACTIVE){
                //탈퇴한 회원 "INACTIVE"상태는 재가입이 불가하도록 설정
                throw new EmailAlreadyExistsException("탈퇴한 회원 아이디로는 재가입이 불가능합니다");
            }else{
                throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다");
            }
        }

        String encryptedPassword = passwordEncoder.encode(signUpRequest.getUserPassword());

        UserDto userDto = new UserDto();
        userDto.createUser(signUpRequest, encryptedPassword);

        userMapper.insertUser(userDto);
        insertUserPet(signUpRequest);
    }

    //회원의 반려동물의 정보를 저장하는 메서드
    private  void insertUserPet(SignUpRequest signUpRequest){
        int userNum = userMapper.getLastInsertUserNum();
        for(int petTypeId : signUpRequest.getPetTypeIds()){
            UserPetDto userPetDto = UserPetDto.builder()
                    .userNum(userNum)
                    .petTypeId(petTypeId)
                    .build();
            petMapper.insertUserPet(userPetDto);
        }

    }

    //회원로그인 메서드
    @Override
    public SignInResponseDto authenticateUser(String userEmail, String userPassword) {
        // 사용자 정보 검증
        UserDto existingUser = validateUser(userEmail, userPassword);
        // 반려동물 정보 조회
        List<Map<String, Object>> petTypes = getPetTypes(existingUser);
        // 새로운 반려동물 정보로 사용자 정보 갱신
        UserDto updatedUser = updateUserWithPetInfo(existingUser, petTypes);
        // 응답 생성 및 반환
        return createSignInResponse(updatedUser);
    }

    // 사용자 정보를 검증하는 메서드
    private UserDto validateUser(String userEmail, String userPassword) {
        UserDto userDto = userMapper.findByEmail(userEmail);
        if (userDto == null || !BCrypt.checkpw(userPassword, userDto.getUserPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return userDto;
    }

    // 사용자의 반려동물 정보를 조회하는 메서드
    private List<Map<String, Object>> getPetTypes(UserDto userDto) {
        List<Map<String, Object>> petTypes = petMapper.findPetTypesByUserNum(userDto.getUserNum());
        System.out.println("Returned petTypes: " + petTypes);
        return petTypes;

    }

    // 사용자 정보와 새로운 반려동물 정보를 합치는 메서드
    private UserDto updateUserWithPetInfo(UserDto userDto, List<Map<String, Object>> petTypes) {
        List<Integer> petTypeIds = extractPetTypeIds(petTypes);
        List<String> petTypeNames = extractPetTypeNames(petTypes);

        // 반려동물 정보를 UserDto 객체에 담는다.
        userDto.setPetTypeIds(petTypeIds);
        userDto.setPetTypes(petTypeNames);
        return userDto;
    }

    // 반려동물 유형 ID를 추출하는 메서드
    private List<Integer> extractPetTypeIds(List<Map<String, Object>> petTypes) {
        List<Integer> petTypeIds = new ArrayList<>();
        for(Map<String, Object> petType : petTypes) {
            petTypeIds.add((Integer) petType.get("petTypeId"));
        }
        return petTypeIds;
    }

    // 반려동물 이름을 추출하는 메서드
    private List<String> extractPetTypeNames(List<Map<String, Object>> petTypes) {
        List<String> petTypeNames = new ArrayList<>();
        for(Map<String, Object> petType : petTypes) {
            petTypeNames.add((String) petType.get("petName"));
        }
        return petTypeNames;
    }

    // 로그인 응답을 생성하는 메서드
    private SignInResponseDto createSignInResponse(UserDto updatedUser) {
        String token = tokenProvider.create(updatedUser);
        int exprTime = 3600;  // 토큰 만료 시간
        return new SignInResponseDto(token, exprTime, updatedUser);
    }

    //이메일 중복 검사
    @Override
    public boolean isEmailDuplicated(String userEmail) {
        int count = userMapper.checkEmailDuplication(userEmail);
        return count > 0;
    }
}
