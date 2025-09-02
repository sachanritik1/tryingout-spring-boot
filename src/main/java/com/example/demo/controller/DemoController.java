package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.services.UserService;

@RestController
public class DemoController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String hello() {
        return userService.getUser();
    }

    @PostMapping("/post")
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "User created successfully";
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
