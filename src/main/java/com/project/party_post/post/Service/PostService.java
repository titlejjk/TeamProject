package com.project.party_post.post.Service;

import com.project.party_post.post.dto.PostDto;
import com.project.party_post.post.dto.PostImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    //게시글 생성
    void insertPost(PostDto postDto);
    //전체 게시글 조회
    List<PostDto> selectAllPosts();
    //하나의 게시글을 조회
    PostDto selectPostById(int postId);
    //게시글 수정
    void updatePost(PostDto postDto);
    //게시글 삭제
    void deletePost(int postId);
    //이미지 업로드 메서드
    void insertImages(List<MultipartFile> multipartFiles, int postId);
    //이미지 조회 메서드
    List<PostImageDto> getImagesByPostId(int postId);
    //이미지 삭제 메서드
    public void deleteImagesByPostId(int postId);
    //게시글 ID로 게시글 정보를 가져오며, 조회수를 1 증가
    void incrementViewCount(int postId);
}
