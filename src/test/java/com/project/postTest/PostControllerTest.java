package com.project.postTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.party_post.post.service.PostService;
import com.project.party_post.post.dto.PostDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("새글 작성 메서드 테스트")
    public void createPostTest() throws Exception {
        PostDto postDto = new PostDto();
        mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("모든 글 목록 조회 테스트")
    public void getAllPostsTest() throws Exception {
        given(postService.selectAllPosts()).willReturn(Arrays.asList(new PostDto(), new PostDto()));

        mockMvc.perform(get("/post")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시물과 이미지 삭제 테스트")
    public void testDeletePostAndImages() throws Exception {
        // Assume that postId 1 exists in the database and has associated images

        // Perform DELETE request to delete the post and its images
        mockMvc.perform(MockMvcRequestBuilders.delete("/post/{postId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Expect HTTP 200 OK status

        // Additional assertions can be added to further validate database state
    }
}
