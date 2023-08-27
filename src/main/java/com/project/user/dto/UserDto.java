package com.project.user.dto;

import com.project.user.dto.UserEnumClass.UserGender;
import com.project.user.dto.UserEnumClass.UserRole;
import com.project.user.dto.UserEnumClass.UserStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
//모든 파라미터 생성자 생성
@AllArgsConstructor
@Builder
public class UserDto {

    private int userNum;
    private String userEmail; //회원의 E-mail type ID
    private String userPassword; //회원의 비밀번호
    private String userPasswordCheck;
    private String userNickname; //회원의 닉네임
    private UserGender userGender; //회원의 성별
    private String userBirthday; //회원의 생일
    private String userProfile; //회원의 프로필사진 경로
    private MultipartFile userImage; //회원의 프로필 image file
    private String userIntroduction; //회원의 한줄소개
    private UserStatus userStatus; //회원의 활동상태
    private Date userCreatedAt; //회원의 가입날짜
    private Date userUpdatedAt; //회원의 회원정보 수정날짜
    private UserRole role; //사용자 역할(일반사용자, 관리자)

    private List<Integer> petTypeIds; // petTypeIds 추가
    private List<String> petTypes = new ArrayList<>();;//반려동물 타입목록

    //기존 사용자 정보와 새로운 프로필 이미지 경로를 합쳐서 정적메서드로 사용
    public static UserDto fromExistingAndUpdateProfile(UserDto existingUser, String updateProfilePath) {
        return UserDto.builder()
                .userNum(existingUser.getUserNum())
                .userEmail(existingUser.getUserEmail())
                .userNickname(existingUser.getUserNickname())
                .userGender(existingUser.getUserGender())
                .userBirthday(existingUser.getUserBirthday())
                .userProfile(existingUser.getUserProfile())
                .userProfile(existingUser.getUserProfile())
                .userImage(existingUser.getUserImage())
                .petTypeIds(existingUser.getPetTypeIds())
                .petTypes(existingUser.getPetTypes())
                .build();
    }


    public void createUser(SignUpRequest signUpRequest, String encryptedPassword) {
        this.userEmail = signUpRequest.getUserEmail();
        this.userPassword = encryptedPassword;
        this.userNickname = signUpRequest.getUserNickname();
        this.petTypeIds = signUpRequest.getPetTypeIds();
    }

    // UserDto 클래스 내부에 추가

    // 기존 사용자 정보와 새로운 반려동물 정보를 합쳐 새 UserDto 객체를 정적팩토리 메서드로 생성
    public static UserDto fromExistingAndUpdatedPetInfo(UserDto existingUser, List<Integer> newPetTypeIds, List<String> newPetTypeNames) {
        return UserDto.builder()
                .userNum(existingUser.getUserNum())
                .userEmail(existingUser.getUserEmail())
                .userPassword(existingUser.getUserPassword())
                .userPasswordCheck(existingUser.getUserPasswordCheck())
                .userNickname(existingUser.getUserNickname())
                .userGender(existingUser.getUserGender())
                .userBirthday(existingUser.getUserBirthday())
                .userProfile(existingUser.getUserProfile())
                .userImage(existingUser.getUserImage())
                .userIntroduction(existingUser.getUserIntroduction())
                .userStatus(existingUser.getUserStatus())
                .userCreatedAt(existingUser.getUserCreatedAt())
                .userUpdatedAt(existingUser.getUserUpdatedAt())
                .role(existingUser.getRole())
                .petTypeIds(newPetTypeIds)
                .petTypes(newPetTypeNames)
                .build();
    }
}
