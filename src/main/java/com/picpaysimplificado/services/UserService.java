package com.picpaysimplificado.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) {
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new IllegalArgumentException("Merchant cannot make transactions");
        }
        if(sender.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User saveUser(User user) {
        return repository.save(user);
    }
}
