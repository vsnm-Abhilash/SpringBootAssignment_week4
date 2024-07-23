package com.abhilash.sb.assignment_w4.repositories;

import com.abhilash.sb.assignment_w4.entities.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository<PostsEntity,Long> {
}
