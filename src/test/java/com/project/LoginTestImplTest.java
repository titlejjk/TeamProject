package com.project;

import com.project.user.dao.PetMapper;
import com.project.user.dao.UserMapper;
import com.project.user.dto.SignUpRequest;
import com.project.user.dto.UserDto;
import com.project.user.service.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginTestImplTest {

    //인스턴스생성 후 생성된 Mock객체를 자동으로 주입
    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PetMapper petMapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원가입이 정상동작 함")
    public void testRegisterUserSuccessfully() {

        //Given
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserEmail("test@ex.com");
        signUpRequest.setUserPassword("testPwd");
        signUpRequest.setPetTypeIds(Arrays.asList(1, 2));

        String encryptedPassword = passwordEncoder.encode(signUpRequest.getUserPassword());
        UserDto userDto = new UserDto();
        userDto.createUser(signUpRequest, encryptedPassword);

        //When
        authService.signUp(signUpRequest);

        //Then
        verify(userMapper, times(1)).insertUser(any(UserDto.class));
        verify(petMapper, times(2)).insertUserPet(any());
    }
}
