package com.teay.finance.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(unique = true)
    private String username;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Roles userRole;

    public User(){
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Roles getUserRole() {
        return userRole;
    }

    public void setUserRole(Roles userRole) {
        this.userRole = userRole;
    }
}
