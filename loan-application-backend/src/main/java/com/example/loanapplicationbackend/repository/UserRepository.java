package com.example.loanapplicationbackend.repository;

import com.example.loanapplicationbackend.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public User findUserByUsername(String username) {
        return new User(username, "123456");
    }
}
