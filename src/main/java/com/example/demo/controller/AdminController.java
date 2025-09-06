package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Journal;
import com.example.demo.entities.User;
import com.example.demo.services.JournalService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping("/journals")
    public ResponseEntity<List<Journal>> getAllJournals() {
        List<Journal> journals = journalService.getAllJournals();
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping("/journals/{id}")
    public ResponseEntity<Journal> getJournal(@PathVariable String id) {
        Journal journal = journalService.getJournal(id);
        if (journal != null) {
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = userService.getUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
