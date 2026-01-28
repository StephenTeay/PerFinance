package com.teay.finance.controllers;

import com.teay.finance.dtos.CategoryRequest;
import com.teay.finance.entities.Category;
import com.teay.finance.services.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryRequest request){
        return new ResponseEntity<>(categoryServiceImpl.createCategory(request), HttpStatus.CREATED) ;
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<Category>>  getCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return new ResponseEntity<>(categoryServiceImpl.findCategory(page,size),HttpStatus.FOUND) ;
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        categoryServiceImpl.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
