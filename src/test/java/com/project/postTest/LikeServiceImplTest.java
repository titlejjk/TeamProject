package com.project.postTest;

import com.project.party_post.post_like.dao.LikeMapper;
import com.project.party_post.post_like.dto.LikeDto;
import com.project.party_post.post_like.service.LikeServiceImpl;
import com.project.user.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LikeServiceImplTest {
    @InjectMocks
    private LikeServiceImpl likeService;

    @Mock
    private LikeMapper likeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertLike() {

        LikeDto likeDto = new LikeDto(1, 1, 1, "2023-09-03");
        doNothing().when(likeMapper).insertLike(likeDto);
        likeService.insertLike(likeDto);
        verify(likeMapper, times(1)).insertLike(likeDto);
    }

    @Test
    void testDeleteLike() {
        int likeId = 1;
        doNothing().when(likeMapper).deleteLike(likeId);
        likeService.deleteLike(likeId);
        verify(likeMapper, times(1)).deleteLike(likeId);
    }

    @Test
    void testCountLikesByPostId() {
        int postId = 1;
        when(likeMapper.countLikesByPostId(postId)).thenReturn(5);
        int count = likeService.countLikesByPostId(postId);
        assertEquals(5, count);
    }

    @Test
    void testFindLikeByUserAndPost() {
        int userNum = 1;
        int postId = 1;
        when(likeMapper.findLikeByUserAndPost(userNum, postId)).thenReturn(1);
        int likeId = likeService.findLikeByUserAndPost(userNum, postId);
        assertEquals(1, likeId);
    }

}
