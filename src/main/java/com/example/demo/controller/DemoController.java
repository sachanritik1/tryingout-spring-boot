package com.example.demo.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> hello(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
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
