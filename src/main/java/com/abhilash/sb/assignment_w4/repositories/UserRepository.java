package com.abhilash.sb.assignment_w4.repositories;

import com.abhilash.sb.assignment_w4.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
