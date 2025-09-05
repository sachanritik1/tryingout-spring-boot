package com.example.demo.services;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.entities.Journal;
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

    public User updateUser(String id, UpdateUserRequest user) {
        User existingUser = this.getUser(id);
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        existingUser.setUpdatedAt(new Date());
        userRepository.save(existingUser);
        return existingUser;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(new ObjectId(id));
    }

    public void addJournalToUser(String userId, Journal journal) {
        User user = userRepository.findById(new ObjectId(userId)).orElse(null);
        if (user != null) {
            user.getJournals().add(journal);
            userRepository.save(user);
        }
    }
}
