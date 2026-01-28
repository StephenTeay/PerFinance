package com.teay.finance.services;

import com.teay.finance.Type;
import com.teay.finance.dtos.TransactionRequest;
import com.teay.finance.entities.Transaction;
import com.teay.finance.entities.User;
import com.teay.finance.exceptions.TransactionException;
import com.teay.finance.exceptions.TransactionNotFoundException;
import com.teay.finance.exceptions.UserNotFoundException;
import com.teay.finance.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final UserServiceImpl userServiceImpl;
    private final CategoryServiceImpl categoryServiceImpl;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserServiceImpl userServiceImpl, CategoryServiceImpl categoryServiceImpl) {
        this.transactionRepository = transactionRepository;

        this.userServiceImpl = userServiceImpl;
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @Override
    public Transaction createTransaction(TransactionRequest request) {
        if(request.getType().equals(Type.EXPENSE)){
            if(validTransaction(request)){
                Transaction newTransaction = new Transaction();
                newTransaction.setAmount(request.getAmount());
                userServiceImpl.updateUserBalance(request.getType(),request);
                newTransaction.setCategory(categoryServiceImpl.findACategory(request.getCategoryId()));
                newTransaction.setType(request.getType());
                newTransaction.setToday(request.getToday());
                newTransaction.setUser(userServiceImpl.getUser(request.getUserId()));
                newTransaction.setDescription(request.getDescription());
                transactionRepository.save(newTransaction);
                return newTransaction;
            }
            else{
                throw new TransactionException("You cannot make an expense beyond your balance");
            }

        }
        else{
            Transaction newTransaction = new Transaction();
            newTransaction.setAmount(request.getAmount());
            userServiceImpl.updateUserBalance(request.getType(),request);
            newTransaction.setCategory(categoryServiceImpl.findACategory(request.getCategoryId()));
            newTransaction.setType(request.getType());
            newTransaction.setToday(request.getToday());
            newTransaction.setUser(userServiceImpl.getUser(request.getUserId()));
            newTransaction.setDescription(request.getDescription());
            transactionRepository.save(newTransaction);
            return newTransaction;
        }
    }

    public boolean validTransaction(TransactionRequest request){
        User existingUser = userServiceImpl.getUser(request.getUserId());
        BigDecimal userBalance = existingUser.getBalance();
        BigDecimal transactionAmount = request.getAmount();
        int result = transactionAmount.compareTo(userBalance);
        return result != 1;
    }

    @Override
    public Transaction findTransaction( Long transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(()-> new TransactionNotFoundException("Transaction doesn't exist"));
    }

    @Override
    public Page<Transaction> findAllUserTransaction(Long userId, int page, int size) {
        Optional <User> existingUser = userServiceImpl.findUser(userId);
        if(existingUser.isEmpty()){
            throw new UserNotFoundException("User doesn't exist");
        }
        else{
            Pageable pageable = PageRequest.of(page,size);
            return transactionRepository.findAllByUser(existingUser.get(),pageable);
        }

    }


}
