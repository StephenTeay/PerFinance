package com.teay.finance.repositories;

import com.teay.finance.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category,Long> {
    boolean getCategoryByCategoryName(String categoryName);
}
