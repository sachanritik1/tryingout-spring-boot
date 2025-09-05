package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.User;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> hello(@PathVariable("id") String id) {
        User user = userService.getUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest req) {
        try {
            User user = userService.createUser(req);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/patch")
    public String updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return "User updated successfully";
    }

    @PutMapping("/put")
    public String replaceUser(@RequestBody User user) {
        userService.replaceUser(user);
        return "User replaced successfully";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return "User deleted successfully";
    }

}
