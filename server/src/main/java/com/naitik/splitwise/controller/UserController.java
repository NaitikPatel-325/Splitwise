package com.naitik.splitwise.controller;
import com.naitik.splitwise.entity.User;
import com.naitik.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<User> getUser(@RequestParam String username, @RequestParam String password) {
        return userService.getUser(username, password);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
