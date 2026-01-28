package com.teay.finance.services;

import com.teay.finance.dtos.TransactionRequest;
import com.teay.finance.entities.Transaction;
import org.springframework.data.domain.Page;

public interface TransactionService {
    Transaction createTransaction(TransactionRequest request);
    Transaction findTransaction( Long transactionId);
    Page<Transaction> findAllUserTransaction(Long userId,int page, int size);

}
