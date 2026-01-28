package com.teay.finance.services;

import com.teay.finance.Type;
import com.teay.finance.dtos.TransactionRequest;
import com.teay.finance.dtos.UserRequest;
import com.teay.finance.entities.Transaction;
import com.teay.finance.entities.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserRequest request);
    BigDecimal getUserBalance(@RequestParam Long userId);
    Optional<User> findUser(Long id);
    void updateUserBalance(Type type,TransactionRequest request);
}
