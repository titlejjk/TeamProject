package com.project.party_post.post.Service;

import com.project.file_service.FileUploadService;
import com.project.party_post.post.dao.PostMapper;
import com.project.party_post.post.dto.PostDto;
import com.project.party_post.post.dto.PostImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    private final FileUploadService fileUploadService;


    @Override
    public void insertPost(PostDto postDto) {
        postMapper.insertPost(postDto);
    }

    @Override
    public List<PostDto> selectAllPosts() {
        return postMapper.selectAllPosts();
    }

    @Override
    public PostDto selectPostById(int postId) {
        return postMapper.selectPostById(postId);
    }

    @Override
    public void updatePost(PostDto postDto) {
        PostDto updatedPost = PostDto.builder()
                .postId(postDto.getPostId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        postMapper.updatePost(updatedPost);
    }

    @Override
    public void deletePost(int postId) {
        // 먼저 게시물에 연관된 이미지들을 삭제
        deleteImagesByPostId(postId);

        // 그 후 게시물 삭제
        postMapper.deletePost(postId);
    }

    @Override
    public void insertImages(List<MultipartFile> multipartFiles, int postId) {
        for (MultipartFile multipartFile : multipartFiles) {
            String path = fileUploadService.uploadFile(multipartFile, "posts");
            PostImageDto postImageDto = PostImageDto.builder()
                    .postId(postId)
                    .imageUrl(path)
                    .build();
            postMapper.insertImage(postImageDto);
        }
    }

    @Override
    public List<PostImageDto> getImagesByPostId(int postId) {
        return postMapper.selectImagesByPostId(postId);
    }

    @Override
    public void deleteImagesByPostId(int postId) {
        postMapper.deleteImagesByPostId(postId);
    }

    @Override
    public void incrementViewCount(int postId) {
        postMapper.incrementViewCount(postId);
    }
}
