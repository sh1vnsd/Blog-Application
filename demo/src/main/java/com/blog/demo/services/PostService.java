package com.blog.demo.services;

import java.util.List;
import com.blog.demo.models.Post;
import com.blog.demo.payloads.PostDto;
import com.blog.demo.payloads.PostResponse;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update post
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete Post
    void deletePost(Integer postId);

    //get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);


    //get single post
    PostDto getPostById(Integer postId);

    //get all posts by category

    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all posts by user

    List<PostDto> getPostsByUser(Integer userId);

    //search posts
    List<PostDto> searchPosts(String keyword);
}
