package com.teay.finance.controllers;

import com.teay.finance.dtos.TransactionRequest;
import com.teay.finance.entities.Transaction;
import com.teay.finance.services.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    private final TransactionServiceImpl transactionServiceImpl;


    public TransactionController(TransactionServiceImpl transactionServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
    }

    @Operation(summary = "Create Transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction Made"),
            @ApiResponse(responseCode = "400", description = "Invalid Input")
    })
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionRequest request){
        return new ResponseEntity<>(transactionServiceImpl.createTransaction(request), HttpStatus.CREATED);
    }
}
