package com.teay.finance.services;

import com.teay.finance.dtos.CategoryRequest;
import com.teay.finance.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CategoryService {
    public Category createCategory(CategoryRequest request);
    public Page<Category> findCategory(int page, int size);
    public void deleteCategory(@RequestParam Long categoryId);
    public Category findACategory(Long categoryId);

}
