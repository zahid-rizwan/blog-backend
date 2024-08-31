package com.backend.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.entities.Comment;
import com.backend.blog.entities.Post;
import com.backend.blog.exceptions.ResourceNotFoundException;
import com.backend.blog.payloads.CommentDto;
import com.backend.blog.repositories.CommentRepo;
import com.backend.blog.repositories.PostRepo;
import com.backend.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("poat", "postId", postId));
       Comment comment =  this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment saveComment = this.commentRepo.save(comment);

        return this.modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public void delete(Integer commentId) {
       Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "Comment Id", commentId));
       this.commentRepo.delete(comment); 
    }
    
}
