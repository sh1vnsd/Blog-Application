package com.blog.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.demo.models.Category;
import com.blog.demo.models.Post;
import com.blog.demo.models.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

    //Custom Finder Methods
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
