package com.project.party_post.post.controller;

import com.project.party_post.post.Service.PostService;
import com.project.party_post.post.dto.PostImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post/{postId}/images")
@RequiredArgsConstructor
public class PostImageController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> uploadImages(@PathVariable int postId, @RequestParam("files") List<MultipartFile> multipartFiles){
        postService.insertImages(multipartFiles, postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<PostImageDto>> getImagesByPostId(@PathVariable int postId){
        return ResponseEntity.ok(postService.getImagesByPostId(postId));
    }
}
