package com.teay.finance.repositories;

import com.teay.finance.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category,Long> {
    boolean getCategoryByCategoryName(String categoryName);
}
