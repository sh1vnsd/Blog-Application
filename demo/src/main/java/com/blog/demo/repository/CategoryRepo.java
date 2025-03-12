package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.demo.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    
}
