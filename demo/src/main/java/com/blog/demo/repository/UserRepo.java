package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.demo.models.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
