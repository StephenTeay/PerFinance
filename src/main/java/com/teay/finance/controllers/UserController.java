package com.teay.finance.controllers;

import com.teay.finance.dtos.UserRequest;
import com.teay.finance.entities.Transaction;
import com.teay.finance.entities.User;
import com.teay.finance.services.TransactionServiceImpl;
import com.teay.finance.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/")
    public String home(HttpServletRequest request){
        return "Welcome to Personal Finance Tracker Application \n"+"Confirm if the URL provided: "+request.getRequestURI();
    }

    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Input")
    })
    @PostMapping("/register/")
    public ResponseEntity<User>  createUser(@Valid @RequestBody UserRequest request){
        return new ResponseEntity<>(userServiceImpl.createUser(request),HttpStatus.CREATED);
    }

    @Operation(summary = "User Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created Successfully"),
            @ApiResponse(responseCode = "401", description = "Forbidden")
    })
    @PostMapping("/login")
    public ResponseEntity<String>  login(@Valid @RequestBody UserRequest request){
        return new ResponseEntity<String>(userServiceImpl.verifyUser(request),HttpStatus.OK);
    }

    @Operation(summary = "Fetch User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Fetched Successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/users/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id){
        return new ResponseEntity<>(userServiceImpl.getUserBalance(id),HttpStatus.FOUND) ;
    }

    @Operation(summary = "Fetch User's Transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's Transaction Successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/users/{id}/transactions")
    public ResponseEntity<Page<Transaction>> allUserTransactions(@PathVariable Long id,
                                                                 @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){
        return new ResponseEntity<>(transactionServiceImpl.findAllUserTransaction(id, page,size), HttpStatus.OK);
    }
}
