package com.teay.finance.services;

import com.teay.finance.dtos.CategoryRequest;
import com.teay.finance.entities.Category;
import com.teay.finance.entities.User;
import com.teay.finance.exceptions.CategoryNotFoundException;
import com.teay.finance.exceptions.UserNotFoundException;
import com.teay.finance.repositories.CategoriesRepository;
import com.teay.finance.repositories.TransactionRepository;
import com.teay.finance.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final TransactionRepository transactionRepository;
    private final CategoriesRepository categoriesRepository;
    private final UserRepository userRepository;

    public CategoryServiceImpl(UserRepository userRepository, TransactionRepository transactionRepository, CategoriesRepository categoriesRepository, UserRepository userRepository1) {
        this.transactionRepository = transactionRepository;
        this.categoriesRepository = categoriesRepository;
        this.userRepository = userRepository1;
    }

    @Override
    public Category createCategory(CategoryRequest request) {
        Optional<User> existingUser = userRepository.findById(request.getUserId());
        if (existingUser.isEmpty()) {
           throw new UserNotFoundException("User not found");
        }
        Category newCategory = new Category();
        newCategory.setCategoryName(request.getCategoryName());
        categoriesRepository.save(newCategory);
        return newCategory;
    }

    @Override
    public Page<Category> findCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoriesRepository.findAll(pageable);
    }



    @Override
    public void deleteCategory(Long categoryId) {
        if(categoriesRepository.existsById(categoryId)) {
            if (transactionRepository.existsByCategoryId(categoryId)) {
                throw new CategoryNotFoundException("A Category cannot be deleted when Transactions are linked");
            }
            categoriesRepository.deleteById(categoryId);
        }
        else{
            throw new CategoryNotFoundException("No such Category exist");
        }
    }

    @Override
    public Category findACategory(Long categoryId) {
     return categoriesRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
    }


}
