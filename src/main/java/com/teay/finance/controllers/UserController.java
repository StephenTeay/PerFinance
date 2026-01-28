package com.teay.finance.controllers;

import com.teay.finance.dtos.UserRequest;
import com.teay.finance.entities.Transaction;
import com.teay.finance.entities.User;
import com.teay.finance.services.TransactionServiceImpl;
import com.teay.finance.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final TransactionServiceImpl transactionServiceImpl;


    public UserController(UserServiceImpl userServiceImpl, TransactionServiceImpl transactionServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.transactionServiceImpl = transactionServiceImpl;

    }

    @PostMapping("/users/")
    public ResponseEntity<User>  createUser(@Valid @RequestBody UserRequest request){
        return new ResponseEntity<>(userServiceImpl.createUser(request),HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id){
        return new ResponseEntity<>(userServiceImpl.getUserBalance(id),HttpStatus.FOUND) ;
    }

    @GetMapping("/users/{id}/transactions")
    public ResponseEntity<Page<Transaction>> allUserTransactions(@PathVariable Long id,
                                                                 @RequestParam(defaultValue="0") int page,
                                                                 @RequestParam(defaultValue="10") int size){
        return new ResponseEntity<>(transactionServiceImpl.findAllUserTransaction(id, page,size), HttpStatus.OK);
    }
}
