package com.project.project.user.controller;

import com.project.project.user.dto.UserDto;
import com.project.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원 정보 수정 메서드
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@ModelAttribute UserDto userDto, @RequestParam("file")MultipartFile userImage) {
        try {
            //회원 정보와 회원의 profile image update
            userService.updateUserInfo(userDto, userImage);
            return new ResponseEntity<>("회원 정보가 성공적으로 수정되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            //예외 발생시 응답
            return new ResponseEntity<>("회원 정보 수정에 실패하였습니다."+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //회원 탈퇴 메서드
    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivate(@RequestParam int userNum){
        userService.deactivateUser(userNum);
        return new ResponseEntity<>("회원 탈퇴가 성공적으로 처리되었습니다", HttpStatus.OK);
    }
}
