package com.teay.finance.services;

import com.teay.finance.Type;
import com.teay.finance.dtos.TransactionRequest;
import com.teay.finance.dtos.UserRequest;
import com.teay.finance.entities.User;
import com.teay.finance.exceptions.UserNotFoundException;
import com.teay.finance.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;


    public UserServiceImpl(AuthenticationManager authenticationManager, BCryptPasswordEncoder encoder, UserRepository userRepository, JwtService jwtService){
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public User createUser(UserRequest request){
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(encoder.encode(request.getPassword()));
        newUser.setBalance(request.getBalance());
        newUser.setUserRole(request.getRole());
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
            User user = existingUser.get();
            BigDecimal userBalance = user.getBalance();
            if (request.getType().equals(Type.EXPENSE)){
                BigDecimal newBalance = userBalance.subtract(request.getAmount());
                user.setBalance(newBalance);
            }
            else{
                BigDecimal newBalance = userBalance.add(request.getAmount());
                user.setBalance(newBalance);
            }
        }
        else{
            throw new UserNotFoundException("User doesn't exist");
        }


    }

    public BigDecimal getUserBalance(Long userId){
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not Found"));
        return existingUser.getBalance();
    }

    public User getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    public String verifyUser(UserRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(request.getUsername()) ;
        }

        return "Not authenticated";
    }




}
