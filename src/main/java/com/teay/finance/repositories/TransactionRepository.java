package com.teay.finance.repositories;

import com.teay.finance.entities.Transaction;
import com.teay.finance.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public Page<Transaction> findAllByUser(User user, Pageable pageable);
    public boolean existsByCategoryId(Long categoryId);

}
