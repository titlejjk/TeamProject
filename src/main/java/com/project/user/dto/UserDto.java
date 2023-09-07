package com.project.user.dto;

import com.project.user.dto.UserEnumClass.UserGender;
import com.project.user.dto.UserEnumClass.UserRole;
import com.project.user.dto.UserEnumClass.UserStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
//모든 파라미터 생성자 생성
@AllArgsConstructor
@Builder(toBuilder = true)//부분업데이트 지원
public class UserDto {

    private int userNum;
    private String userEmail; //회원의 E-mail type ID
    private String userPassword; //회원의 비밀번호
    private String userNewPassword; //회원의 새로운 비밀번호
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

    public void createUser(SignUpRequest signUpRequest, String encryptedPassword) {
        this.userEmail = signUpRequest.getUserEmail();
        this.userPassword = encryptedPassword;
        this.userNickname = signUpRequest.getUserNickname();
        this.petTypeIds = signUpRequest.getPetTypeIds();
    }


}
