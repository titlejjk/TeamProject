package com.project.party_post.Service;

import com.project.party_post.dao.PostDao;
import com.project.party_post.dto.PostDto;
import com.project.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostDao postDao;
    @Override
    public void createPost(PostDto postDto) {
        UserDto user = UserDto.builder()
                .userNum(postDto.getUser().getUserNum())
                .userEmail(postDto.getUser().getUserEmail())
                .build();

        PostDto post = PostDto.builder()
                .postId(postDto.getPostId())
                .user(user)
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        postDao.insertPost(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postDao.selectAllPosts();
    }

    @Override
    public PostDto getPostById(int postId) {
        return postDao.selectPostById(postId);
    }

    @Override
    public void updatePost(int postId, PostDto postDto) {
        UserDto updatedUser = UserDto.builder()
                .userNum(postDto.getUser().getUserNum())
                .userEmail(postDto.getUser().getUserEmail())
                .build();

        PostDto updatedPost = PostDto.builder()
                .postId(postDto.getPostId())
                .user(updatedUser)
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        postDao.updatePost(updatedPost);
    }

    @Override
    public void deletePost(int post_id) {
        postDao.deletePost(post_id);
    }
}
