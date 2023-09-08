package com.project.user.dao;

import com.project.user.dto.EmailVerificationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailVerificationMapper {

    //이메일 인증 코드 저장
    void insertEmailCode(EmailVerificationDto emailVerificationDto);

    //이메일 주소로 인증 코드 검색
    EmailVerificationDto findEmailCodeByUserEmail(String userEmail);

    //인증 코드 삭제
    void deleteEmailCode(String userEmail);
}
