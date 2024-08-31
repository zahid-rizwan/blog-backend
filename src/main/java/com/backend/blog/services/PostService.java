
package com.backend.blog.services;

import java.util.List;

import com.backend.blog.payloads.PostDto;
import com.backend.blog.payloads.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    PostDto getPostById(Integer id);

    PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy);

    void deletePost(Integer postId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);

}