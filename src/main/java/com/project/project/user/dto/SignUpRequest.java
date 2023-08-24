package com.project.project.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    @Email(message = "유효한 이메일 형식.")
    @NotBlank(message = "이메일은 필수 항목.")
    private String userEmail;

    @NotBlank(message = "비밀번호는 필수 항목.")
    @Size(min = 8, message = "비밀번호는 최소 8자리 이상.")
    private String userPassword;

    @NotBlank(message = "닉네임은 필수 항목.")
    private String userNickname;

    // 반려동물 선택 정보 (예: [1, 3, 5])
//    @NotBlank(message = "반려동물 선택은 필수 항목.")
    private List<Integer> petTypeIds;


}
