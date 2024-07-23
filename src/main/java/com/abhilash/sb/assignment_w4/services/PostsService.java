package com.abhilash.sb.assignment_w4.services;


import com.abhilash.sb.assignment_w4.dto.PostsDTO;

import java.util.List;

public interface PostsService {
    List<PostsDTO> getAllPosts();
    PostsDTO createPost(PostsDTO postsDTO);
    PostsDTO getPostById(Long id);
    PostsDTO updatePostById(PostsDTO postsDTO, Long postId);
}
