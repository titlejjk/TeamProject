package com.project.postTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.party_post.comment.dto.CommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCommentTest() throws Exception {
        CommentDto commentDto = new CommentDto(1, 1, 1, "This is a test comment", "2023-09-03", "2023-09-03");
        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentDto)))
                .andExpect(status().isOk());
    }

    @Test
    void updateCommentTest() throws Exception {
        CommentDto commentDto = new CommentDto(1, 1, 1, "This is an updated comment", "2023-09-03", "2023-09-03");
        mockMvc.perform(put("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCommentTest() throws Exception {
        mockMvc.perform(delete("/comments")
                        .param("commentId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void getCommentByIdTest() throws Exception {
        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getCommentsByPostIdTest() throws Exception {
        mockMvc.perform(get("/comments/post/1"))
                .andExpect(status().isOk());
    }
}
