package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String getUser() {
        return userRepository.fetchUser();
    }

    public void createUser(User user) {
        userRepository.saveUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void replaceUser(User user) {
        userRepository.replaceUser(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }
}
