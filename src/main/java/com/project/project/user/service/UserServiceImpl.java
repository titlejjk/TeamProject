package com.project.project.user.service;

import com.project.project.user.dao.PetMapper;
import com.project.project.user.dao.UserMapper;
import com.project.project.user.dto.UserDto;
import com.project.project.user.dto.UserPetDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Builder
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    //UserMapper 의존성주입
    private final UserMapper userMapper;
    //PetMapper 의존성주입
    private final PetMapper petMapper;
    //파일 업로드
    private final FileUploadService fileUploadService;

    //회원정보 수정 메서드
    @Transactional
    @Override
    public void updateUserInfo(UserDto userDto, MultipartFile userImage) {
        //mFile이 null이거나 비어있는 경우 파일 업로드 부분을 건너뛰고 나머지 로직을 실행
        if(userImage != null && !userImage.isEmpty()){
            //파일 업로드 로직 수행
            String savedFilePath = fileUploadService.uploadFile(userImage);
            //저장된 파일 경로를 DTO에 설정
            userDto.setUserProfile(savedFilePath);
        }
        // 회원 정보 업데이트
        userMapper.updateUser(userDto);
        // 기존 반려동물 정보 삭제
        petMapper.deletePetsByUserNum(userDto.getUserNum());
        // 새 반려동물 정보 삽입
        if (userDto.getPetTypeIds() != null && !userDto.getPetTypeIds().isEmpty()) {
            for (Integer petTypeId : userDto.getPetTypeIds()) {
                UserPetDto userPetDto = UserPetDto.builder()
                        .userNum(userDto.getUserNum())
                        .petTypeId(petTypeId)
                        .build();
                petMapper.insertUserPet(userPetDto);
            }
        }
    }

    //회원탈퇴 메서드(Update Status)
    @Override
    public void deactivateUser(int userNum) {
        //사용자의 Status를 'INACTIVE'로 변경
        userMapper.updateUserStatus("INACTIVE", userNum);
    }
}
