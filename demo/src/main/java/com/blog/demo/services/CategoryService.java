package com.blog.demo.services;

import java.util.List;
import com.blog.demo.payloads.CategoryDto;

public interface CategoryService {
    
    //create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //get
    CategoryDto getCategory(Integer categoryId);
    //get All

    List<CategoryDto> getAllCategories();
}
