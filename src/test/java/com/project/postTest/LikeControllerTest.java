package com.project.postTest;


import com.project.party_post.post_like.controller.LikeController;

import com.project.party_post.post_like.service.LikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LikeControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LikeController likeController;

    @Mock
    private LikeService likeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(likeController).build();
    }

    @Test
    void testInsertLike() throws Exception {
        mockMvc.perform(post("/likes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"likeId\":1,\"postId\":1,\"userNum\":1,\"createdAt\":\"2023-09-03\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteLike() throws Exception {
        mockMvc.perform(delete("/likes?likeId=1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCountLikesByPostId() throws Exception {
        mockMvc.perform(get("/likes/count?postId=1"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindLikeByUserAndPost() throws Exception {
        mockMvc.perform(get("/likes/check?userNum=1&postId=1"))
                .andExpect(status().isOk());
    }
}
