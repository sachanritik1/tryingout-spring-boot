package com.example.demo.services;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

import org.bson.types.ObjectId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String id) {
        return userRepository.findById(new ObjectId(id)).orElse(null);
    }

    public User createUser(CreateUserRequest userRequest) {
        User user = new User();
        user.setId(ObjectId.get());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        return userRepository.save(user);
    }

    public void updateUser(User user) {

    }

    public void replaceUser(User user) {

    }

    public void deleteUser(User user) {

    }
}
