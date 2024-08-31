package com.backend.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.payloads.ApiResponse;
import com.backend.blog.payloads.CategoryDto;
import com.backend.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createUser(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
        return new  ResponseEntity<>(createCategoryDto,HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDto, userId));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("userId") Integer userId){
        this.categoryService.deleteCategory(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllUser(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("userId")Integer userId){
        return ResponseEntity.ok(this.categoryService.getCategory(userId));
    }
}
