package com.project;

import com.project.exception.CustomException.NicknameAlreadyExistsException;
import com.project.file_service.FileUploadService;
import com.project.security.TokenProvider;
import com.project.user.dao.PetMapper;
import com.project.user.dao.UserMapper;
import com.project.user.dto.UserDto;
import com.project.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private PetMapper petMapper;

    @Mock
    private FileUploadService fileUploadService;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto testUserDto;
    private String fakeToken;

    @BeforeEach
    public void setUp() {
        // Initialize fake values
        String fakeImagePath = "fake/image/path";
        fakeToken = "fakeToken";

        // Initialize UserDto
        testUserDto = UserDto.builder()
                .userEmail("test@test.com")
                .userNickname("testNickname")
                .build();

        // Mock behaviors
        when(fileUploadService.uploadFile(any())).thenReturn(fakeImagePath);
        when(tokenProvider.create(any())).thenReturn(fakeToken);
        when(userMapper.findByEmail(any())).thenReturn(testUserDto);
        when(userMapper.findUserByNickname(any())).thenReturn(0);
    }

    @Test
    public void testUpdateUser() throws NicknameAlreadyExistsException {
        // Call the method to test
        String resultToken = userService.updateUser(testUserDto);

        // Assertions
        assertEquals(fakeToken, resultToken, "Token should match the fake token");
    }
}
