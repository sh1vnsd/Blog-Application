package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.demo.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    
}
