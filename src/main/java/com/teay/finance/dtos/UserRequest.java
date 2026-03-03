package com.teay.finance.dtos;

import com.teay.finance.entities.Roles;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UserRequest {
    @NotNull(message="A User must have a name")

    private String username;

    private String password;

    private Roles role;

    @Min(value = 1, message="Balance cannot be 0")
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
