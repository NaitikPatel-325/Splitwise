package com.naitik.splitwise.service;

import com.naitik.splitwise.daojpa.UserDao;
import com.naitik.splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public ResponseEntity<User> getUser(String username, String password) {
        try {
            User user = userDao.findByUsernameAndPassword(username, password);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<User> addUser(User user) {
        try {
            User savedUser = userDao.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in adding user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}