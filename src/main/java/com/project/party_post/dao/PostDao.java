package com.project.party_post.dao;

import com.project.party_post.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostDao {

    //글을 저장
    void insertPost(PostDto postDto);
    //여러개의 글을 조회
    List<PostDto> selectAllPosts();
    //하나의 글을 조회
    PostDto selectPostById(int post_id);
    //하나의 글을 수정
    void updatePost(int postId, PostDto postDto);
    //하나의 글을 삭제
    void deletePost(int post_id);
}
