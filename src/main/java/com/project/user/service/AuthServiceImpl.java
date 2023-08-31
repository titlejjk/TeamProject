package com.project.user.service;

import com.project.security.TokenProvider;
import com.project.user.dao.PetMapper;
import com.project.user.dao.UserMapper;
import com.project.user.dto.SignInResponseDto;
import com.project.user.dto.SignUpRequest;
import com.project.user.dto.UserDto;
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

    // BCryptPasswordEncoder 는 Spring Security 에서 제공하는 비밀번호 암호화 방식 중 하나로,
    // 강력한 해시 함수인 BCrypt 를 사용하여 비밀번호를 안전하게 저장
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Override
    public void signUp(SignUpRequest signUpRequest) {

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
    private List<Map<String, Object>> getPetTypes(UserDto user) {
        return petMapper.findPetTypesByUserNum(user.getUserNum());
    }

    // 사용자 정보와 새로운 반려동물 정보를 합치는 메서드
    private UserDto updateUserWithPetInfo(UserDto userDto, List<Map<String, Object>> petTypes) {
        List<Integer> petTypeIds = extractPetTypeIds(petTypes);
        List<String> petTypeNames = extractPetTypeNames(petTypes);

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

    @Override
    public boolean isEmailDuplicated(String userEmail) {
        int count = userMapper.checkEmailDuplication(userEmail);
        return count > 0;
    }
}
