package com.jeffhb60.backendshoppingcart.service;

import com.jeffhb60.backendshoppingcart.dto.CategoryDTO;
import com.jeffhb60.backendshoppingcart.exception_handling.APIException;
import com.jeffhb60.backendshoppingcart.exception_handling.ProjectResourceNotFoundException;
import com.jeffhb60.backendshoppingcart.response.CategoryResponse;
import com.jeffhb60.backendshoppingcart.model.Category;
import com.jeffhb60.backendshoppingcart.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        List<String> validSortFields = List.of("id"); // Example valid fields
        if (!validSortFields.contains(sortBy)) {
            throw new APIException("Invalid sortBy parameter: " + sortBy);
        }

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        // Rest of the code remains the same
        List<Category> categories = categoryPage.getContent();
        if (categories.isEmpty())
            throw new APIException("No categories exist!!!");

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(pageNumber);
        categoryResponse.setPageSize(pageSize);
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryFromDB != null) {
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists!");
        }
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ProjectResourceNotFoundException("Category", "id", id));

        categoryRepository.delete(category);

        return modelMapper.map(category, CategoryDTO.class);

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
        Category savedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ProjectResourceNotFoundException("Category", "id", id));

        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setId(id);
        savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ProjectResourceNotFoundException("Category", "id", id));

        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryByName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category == null) {
            throw new APIException("Category with the name " + categoryName + " does not exist!");
        }

        return new CategoryDTO(category.getId(), category.getCategoryName());
    }
}
