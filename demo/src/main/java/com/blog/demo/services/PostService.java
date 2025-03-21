package com.blog.demo.services;

import java.util.List;
import com.blog.demo.models.Post;
import com.blog.demo.payloads.PostDto;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update post
    Post updatePost(PostDto postDto, Integer postId);

    //delete Post
    void deletePost(Integer postId);

    //get all post
    List<Post> getAllPosts();


    //get single post
    Post getPostById(Integer postId);

    //get all posts by category

    List<Post> getPostByCategory(Integer categoryId);

    //get all posts by user

    List<Post> getPostByUser(Integer userId);

    //search posts
    List<Post> searchPosts(String keyword);
}
