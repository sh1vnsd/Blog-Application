package com.blog.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.demo.exceptions.ResourceNotFoundException;
import com.blog.demo.models.Comment;
import com.blog.demo.models.Post;
import com.blog.demo.payloads.CommentDto;
import com.blog.demo.repository.CommentRepo;
import com.blog.demo.repository.PostRepo;
import com.blog.demo.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

    //Using post repo cuz to add a comment we require post in which we are going to add the comment
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    //We have to convert this CommentDto into the object of Comment so we use modelMapper here

    @Autowired
    private ModelMapper modelMapper;


    //here we have to insert the CommentDto in the database with the help of this postId
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
        this.commentRepo.delete(com);
    }

}
