package com.jeffhb60.backendshoppingcart.controller;

import com.jeffhb60.backendshoppingcart.dto.CategoryDTO;
import com.jeffhb60.backendshoppingcart.response.CategoryResponse;
import com.jeffhb60.backendshoppingcart.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jeffhb60.backendshoppingcart.config.AppConstants.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/public/category/id/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@Valid @PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping("public/category/name/{categoryName}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@Valid @PathVariable String categoryName) {
        CategoryDTO categoryDTO = categoryService.getCategoryByName(categoryName);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping("/public/category/all")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @Valid @RequestParam(name = "pageNumber", defaultValue = PAGE_NUMBER, required = false) Integer pageNumber,
            @Valid @RequestParam(name = "pageSize", defaultValue = PAGE_SIZE, required = false) Integer pageSize,
            @Valid @RequestParam(name = "sortBy", defaultValue = SORT_CATEGORIES_BY, required = false) String sortBy,
            @Valid @RequestParam(name = "sortOrder", defaultValue = SORT_ORDER, required = false) String sortOrder
    ) {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/admin/category/add")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/category/delete")
    public ResponseEntity<String> deleteCategory(@Valid @RequestParam(name = "id", required = true) Long id) {
        categoryService.deleteCategory(id);
        String deletedCategoryMessage = "Category with id = " + id + " deleted successfully!";
        return new ResponseEntity<>(deletedCategoryMessage, HttpStatus.OK);
    }

    @PutMapping("/admin/category/update")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                      @RequestParam(name = "id", required = true) Long id) {
        CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, id);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }


}
