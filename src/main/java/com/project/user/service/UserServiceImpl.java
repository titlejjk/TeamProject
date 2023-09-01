package com.project.user.service;

import com.project.exception.CustomException.NicknameAlreadyExistsException;
import com.project.exception.CustomException.TokenInvalidException;
import com.project.exception.CustomException.UserNotFoundException;
import com.project.security.TokenProvider;
import com.project.user.dao.PetMapper;
import com.project.user.dao.UserMapper;
import com.project.user.dto.UserDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Builder
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    //UserMapper 의존성추가
    private final UserMapper userMapper;
    //PetMapper 의존성추가
    private final PetMapper petMapper;
    //FileUploadService 의존성추가
    private final FileUploadService fileUploadService;
    //TokenProvider 의존성추가
    private final TokenProvider tokenProvider;
    //비밀번호 암호화
    private final PasswordEncoder passwordEncoder;

    //사용자 프로필을 업데이트하는 메서드
    @Override
    public String updateUser(UserDto userDto) throws NicknameAlreadyExistsException {
        log.info("Initial UserDto : {}", userDto);
        validateUserNickname(userDto.getUserNickname());

        // 이미지 업로드 후 저장 경로를 userProfile에 저장
        String imagePath = uploadImageAndUpdateProfile(userDto);
        userDto.setUserProfile(imagePath);


        UserDto updatedUserDto = UserDto.builder()
                .userNum(userDto.getUserNum())
                .userEmail(userDto.getUserEmail())
                .userNickname(userDto.getUserNickname())
                .userGender(userDto.getUserGender())
                .userBirthday(userDto.getUserBirthday())
                .userProfile(userDto.getUserProfile())
                .build();

        //DB에 회원 정보 업데이트
        userMapper.updateUser(updatedUserDto);

        //업데이트된 회원 정보 다시 가져오기(userEmail로 사용하여 조회)
        UserDto refreshedUser = userMapper.findByEmail(updatedUserDto.getUserEmail());
        log.info("Initial UserDto : {}", userDto);
        //회원정보 수정 후 새로운 토큰 생성
        return generateNewToken(updatedUserDto);
    }

    // 닉네임 중복 검사
    private void validateUserNickname(String userNickname) {
        int count = userMapper.findUserByNickname(userNickname);
        if(count > 0) {
            throw new IllegalArgumentException("Duplicate nickname");
        }
    }

    // 이미지 업로드 및 프로필 경로 업데이트
    private String uploadImageAndUpdateProfile(UserDto userDto) {
        return fileUploadService.uploadFile(userDto.getUserImage());
    }
    // 새 토큰 생성
    private String generateNewToken(UserDto updatedUserDto) {
        System.out.println(updatedUserDto);
        return tokenProvider.create(updatedUserDto);
    }

    //회원탈퇴 메서드(Update Status)
    @Override
    public void deactivateUser(int userNum) {
        //사용자의 Status를 'INACTIVE'로 변경
        userMapper.updateUserStatus("INACTIVE", userNum);
    }

    @Override
    public void updatePassword(String  userEmail, String newPassword) {
        // 1. 유저 존재 여부 확인
        UserDto userDto = userMapper.findByEmail(userEmail);
        if (userDto == null) {
            throw new UserNotFoundException("User does not exist");
        }

        // 2. 새 비밀번호 암호화 및 DB 업데이트
        String encryptedPassword = passwordEncoder.encode(newPassword);
        userMapper.updatePassword(userDto.getUserNum(), encryptedPassword);
    }

    @Override
    public UserDto getUserProfileAndIntroduction(String userEmail) {
        return userMapper.findUserProfileAndIntroductionByUserEmail(userEmail);
    }
}
