package com.abhilash.sb.assignment_w4.services;


import com.abhilash.sb.assignment_w4.dto.PostsDTO;
import com.abhilash.sb.assignment_w4.entities.PostsEntity;
import com.abhilash.sb.assignment_w4.exceptions.ResourceNotFoundException;
import com.abhilash.sb.assignment_w4.repositories.PostsRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostsService {
    private final PostsRepo postsRepo;
    private final ModelMapper mapper;
    @Override
    public List<PostsDTO> getAllPosts() {
        return postsRepo.findAll()
                .stream()
                .map(postsEntity -> mapper.map(postsEntity,PostsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostsDTO createPost(PostsDTO postsDTO) {
        PostsEntity postsEntity=mapper.map(postsDTO,PostsEntity.class);
        return mapper.map(postsRepo.save(postsEntity),PostsDTO.class);
    }

    @Override
    public PostsDTO getPostById(Long id) {
        PostsEntity postsEntity=postsRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id "+id));

        return mapper.map(postsEntity,PostsDTO.class);

    }
    @Override
    public PostsDTO updatePostById(PostsDTO postsDTO, Long postId) {
        PostsEntity postsEntity=postsRepo
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id "+postId));
        postsDTO.setId(postsEntity.getId());
        mapper.map(postsDTO,postsEntity);
        return mapper.map(postsRepo.save(postsEntity),PostsDTO.class);
    }
}
