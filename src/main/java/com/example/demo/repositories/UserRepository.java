package com.example.demo.repositories;

import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public class UserRepository {

    public String fetchUser() {
        return "user";
    }

    public void saveUser(User user) {
    }

    public void updateUser(User user) {
    }

    public void replaceUser(User user) {
    }

    public void deleteUser(User user) {
    }

}
