package com.naitik.splitwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.naitik.splitwise.security.JwtHelper;
import com.naitik.splitwise.entity.User;
import com.naitik.splitwise.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginUser.getUsername());
        final String jwtToken = jwtHelper.generateToken(userDetails);

        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getUser() {
        ResponseEntity<List<User>> alluser = userService.getUsers();
        return alluser;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User newUser) {
        if (userService.userExists(newUser.getUsername())) {
//            System.out.println("Hii2");
            // If the user already exists, return a CONFLICT response
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        ResponseEntity<User> savedUser = userService.addUser(newUser);

        // Generate JWT token
//        System.out.println("Hii3");
        final UserDetails userDetails = userService.loadUserByUsername(savedUser.getBody().getUsername());
//        System.out.println("Hii3");
        final String jwtToken = jwtHelper.generateToken(userDetails);
//        System.out.println(jwtToken);
        // Return the JWT token in the response body
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/data")
    public ResponseEntity<User> getUserData(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtHelper.extractUsername(jwtToken);
        ResponseEntity<User> user = userService.getUserData(username);
        return ResponseEntity.ok(user.getBody());
    }

}

