package com.abhilash.sb.assignment_w4.controllers;

import com.abhilash.sb.assignment_w4.dto.PostsDTO;
import com.abhilash.sb.assignment_w4.services.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    @GetMapping
    public List<PostsDTO> getAllPosts(){
        return postsService.getAllPosts();
    }

    @PostMapping
    public PostsDTO createPost(@RequestBody PostsDTO postsDTO){
        return postsService.createPost(postsDTO);
    }

    @GetMapping("/{postId}")
    public PostsDTO getPostById(@PathVariable Long postId){
        return postsService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public PostsDTO  updatePostById(@RequestBody PostsDTO postsDTO,@PathVariable Long postId){
        return postsService.updatePostById(postsDTO,postId);
    }

}
