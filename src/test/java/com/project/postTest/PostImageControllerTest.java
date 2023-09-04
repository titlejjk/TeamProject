package com.project.postTest;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.project.party_post.post.Service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class PostImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void testUploadImages() throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("files", "filename1.png", "text/plain", "some-image".getBytes());
        MockMultipartFile secondFile = new MockMultipartFile("files", "filename2.png", "text/plain", "some-image".getBytes());

        doNothing().when(postService).insertImages(anyList(), anyInt());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/post/images/upload")
                        .file(firstFile)
                        .file(secondFile)
                        .param("postId", "1"))
                .andExpect(status().isOk());

        verify(postService, times(1)).insertImages(anyList(), anyInt());
    }
}
