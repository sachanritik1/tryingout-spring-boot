package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;

import com.example.demo.utils.JwtUtils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    public PublicController(UserService userService,
            UserDetailsService userDetailsService,
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello from Public API", HttpStatus.OK);
    }

    @PostMapping("/signup")
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

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest req) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(req.getEmail());

            String token = jwtUtils.generateToken(userDetails.getUsername());

            return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
        } catch (Exception e) {
            log.warn("Login failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Login failed"));
        }

    }
}
