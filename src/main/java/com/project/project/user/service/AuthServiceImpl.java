package com.project.project.user.service;

import com.project.project.exception.CustomException.EmailAlreadyExistsException;
import com.project.project.exception.CustomException.NicknameAlreadyExistsException;
import com.project.project.security.TokenProvider;
import com.project.project.user.dao.PetMapper;
import com.project.project.user.dao.UserMapper;
import com.project.project.user.dto.SignInResponseDto;
import com.project.project.user.dto.SignUpRequest;
import com.project.project.user.dto.UserDto;
import com.project.project.user.dto.UserPetDto;
import lombok.RequiredArgsConstructor;
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

//        //중복된 닉네임 체크
//        if(userMapper.findUserByNickname(signUpRequest.getUserNickname()) > 0){
//            throw new NicknameAlreadyExistsException("이미 사용 중인 닉네임입니다.");
//        }
        String encryptedPassword = passwordEncoder.encode(signUpRequest.getUserPassword());

        //사용자 정보를 변환
        UserDto userDto = UserDto.builder()
                .userEmail(signUpRequest.getUserEmail())
                .userPassword(encryptedPassword)//비밀번호 암호화
                .userNickname(signUpRequest.getUserNickname())
                .petTypeIds(signUpRequest.getPetTypeIds())
                .build();
        System.out.println("UserDto userEmail: " + userDto.getUserEmail());

        //회원 정보 저장
        userMapper.insertUser(userDto);
        int userNum = userMapper.getLastInsertUserNum();
        for (int petTypeId : signUpRequest.getPetTypeIds()) {
            UserPetDto userPetDto = UserPetDto.builder()
                    .userNum(userNum)
                    .petTypeId(petTypeId)
                    .build();
            System.out.println(userPetDto.getUserNum());
            petMapper.insertUserPet(userPetDto);
        }
    }

    @Override
    @Transactional
    public SignInResponseDto authenticateUser(String userEmail, String userPassword) {

        //사용자 정보 조회
        UserDto userDto = userMapper.findByEmail(userEmail);

        // 사용자 정보 또는 비밀번호가 없는 경우의 검증
        if (userDto == null || userDto.getUserPassword() == null) {
            throw new BadCredentialsException("사용자 정보가 없거나 비밀번호가 누락되었습니다.");
        }
        boolean isValid = false;
        isValid = BCrypt.checkpw(userPassword, userDto.getUserPassword());
        //비밀번호 검증
        if(!isValid){
            throw new BadCredentialsException("비밀번호가 불일치합니다");
        }
        //반려동물 유형 정보조회
        List<Map<String, Object>>petTypes = petMapper.findPetTypesByUserNum(userDto.getUserNum());
        System.out.println("여기는 뭐지? : " + petTypes);
        List<Integer> petTypeIds = new ArrayList<>();
        List<String> petTypeNames = new ArrayList<>();
        for(Map<String, Object> petType : petTypes){
            petTypeIds.add((Integer) petType.get("petTypeId"));
            petTypeNames.add((String) petType.get("petName"));
        }
        System.out.println("petTypeIds: " + petTypeIds);
        System.out.println("petTypeNames: " + petTypeNames);

        //반려동물 유형 정보 설정
        UserDto updatedUserDto = UserDto.builder()
                .userNum(userDto.getUserNum())
                .userEmail(userDto.getUserEmail())
                .userPassword(userDto.getUserPassword())
                .userNickname(userDto.getUserNickname())
                .userAddress(userDto.getUserAddress())
                .userGender(userDto.getUserGender())
                .userBirthday(userDto.getUserBirthday())
                .userProfile(userDto.getUserProfile())
                .userIntroduction(userDto.getUserIntroduction())
                .userStatus(userDto.getUserStatus())
                .userCreatedAt(userDto.getUserCreatedAt())
                .userUpdatedAt(userDto.getUserUpdatedAt())
                .role(userDto.getRole())
                .petTypeIds(petTypeIds) //반려동물 유형 ID설정
                .petTypes(petTypeNames) //반려동물 유형 이름 설정
                .build();


        //JWT토큰생성(UserEmail, UserNickName, UserPetType)
        String token = tokenProvider.create(updatedUserDto);
        //토큰 만료시간
        int exprTime = 3600;//1시간
        //응답 Dto생성
        SignInResponseDto response = new SignInResponseDto(token, exprTime, updatedUserDto);

        return response;
    }

    @Override
    public boolean isEmailDuplicated(String userEmail) {
        int count = userMapper.checkEmailDuplication(userEmail);
        return count > 0;
    }
}
