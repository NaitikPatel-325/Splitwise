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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println(loginUser);

        ResponseEntity<User> user = userService.getUserByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());

        System.out.println(user.getBody());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getBody().getUsername(), user.getBody().getPassword()));
            System.out.println("Hii1" + user.getBody().getUsername() + " " + user.getBody().getPassword() + " " + authenticationManager);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginUser.getUsername());
        final String jwtToken = jwtHelper.generateToken(userDetails);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("jwtToken", jwtToken);
        responseMap.put("username", loginUser.getUsername());

        return ResponseEntity.ok(responseMap);
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
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }


        ResponseEntity<User> savedUser = userService.addUser(newUser);
        System.out.println(savedUser.getBody().getUsername());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(savedUser.getBody().getUsername(), savedUser.getBody().getPassword()));

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
        System.out.println("Hii3");
        String username = jwtHelper.extractUsername(jwtToken);
        System.out.println("Hii3");
        ResponseEntity<User> user = userService.getUserData(username);
        return ResponseEntity.ok(user.getBody());
    }

}

