package com.blog.demo.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.blog.demo.models.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.blog.demo.exceptions.ResourceNotFoundException;
import com.blog.demo.models.Post;
import com.blog.demo.models.User;
import com.blog.demo.payloads.PostDto;
import com.blog.demo.payloads.PostResponse;
import com.blog.demo.repository.CategoryRepo;
import com.blog.demo.repository.PostRepo;
import com.blog.demo.repository.UserRepo;
import com.blog.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);

        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        post.setTitle(postDto.getTitle());    
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        this.postRepo.delete(post);
    }

    @Override                       //USED ------- FOR ------- PAGINATION
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() :  Sort.by(sortBy).descending();

        //--> Pagination Started <--
        PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        //--> Pagination Ended <--

        //As we can see we are getting the data in "Post" but we want to convert it to "PostDto "" so we use modelmapper"
        List<PostDto> postDtos = allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNUmber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());



        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
        //As we can see we are getting the data in "Post" but we want to convert it to "PostDto "" so we use modelmapper"
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        //Fetching the category with the categoryId 
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category ID", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);

        //Used to convert List of post entities into a list of PostDto
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        //Fetching the user with userId
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User ID", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

}
