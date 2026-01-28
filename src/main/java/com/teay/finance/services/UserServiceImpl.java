package com.teay.finance.services;

import com.teay.finance.Type;
import com.teay.finance.dtos.TransactionRequest;
import com.teay.finance.dtos.UserRequest;
import com.teay.finance.entities.User;
import com.teay.finance.exceptions.UserNotFoundException;
import com.teay.finance.repositories.TransactionRepository;
import com.teay.finance.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public UserServiceImpl(UserRepository userRepository, TransactionRepository transactionRepository){
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public User createUser(UserRequest request){
        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setBalance(request.getBalance());
        userRepository.save(newUser);
        return newUser;
    }


    @Override
    public Optional<User> findUser(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User doesn't exist")));
    }

    @Override
    public void updateUserBalance(Type type, TransactionRequest request) {
        Optional<User> existingUser = findUser(request.getUserId());
        if(existingUser.isPresent()){
            BigDecimal userBalance = existingUser.get().getBalance();
            if (request.getType().equals(Type.EXPENSE)){
                BigDecimal newBalance = userBalance.subtract(request.getAmount());
                existingUser.get().setBalance(newBalance);
            }
            else{
                BigDecimal newBalance = userBalance.add(request.getAmount());
                existingUser.get().setBalance(newBalance);
            }
        }


    }

    public BigDecimal getUserBalance(Long userId){
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not Found"));
        return existingUser.getBalance();
    }
    public User getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
    }




}
