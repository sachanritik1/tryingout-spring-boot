package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String id) {
        return userRepository.findById(new ObjectId(id));
    }

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public void updateUser(User user) {

    }

    public void replaceUser(User user) {

    }

    public void deleteUser(User user) {

    }
}
