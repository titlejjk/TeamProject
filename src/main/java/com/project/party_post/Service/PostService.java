package com.project.party_post.Service;

import com.project.party_post.dto.PostDto;

import java.util.List;

public interface PostService {

    //게시글 생성
    void createPost(PostDto postDto);

    //게시글 목록 조회
    List<PostDto> getAllPosts();

    //하나의 게시글을 조회
    PostDto getPostById(int post_id);

    //게시글 수정
    void updatePost(int postId, PostDto postDto);

    //게시글 삭제
    void deletePost(int post_id);
}
