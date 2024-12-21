package com.jeffhb60.backendshoppingcart.service;

import com.jeffhb60.backendshoppingcart.dto.CategoryDTO;
import com.jeffhb60.backendshoppingcart.response.CategoryResponse;
import jakarta.validation.Valid;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO addCategory(@Valid CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long id);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

    CategoryDTO getCategoryById(Long id);

    CategoryDTO getCategoryByName(@Valid String categoryName);
}
