package com.blog.demo.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.demo.models.Category;
import com.blog.demo.models.Post;
import com.blog.demo.models.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

    //Custom Finder Methods
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    //by default we dont get the method to search so we have created this
    List<Post> findByTitleContaining(String title);
    
}
