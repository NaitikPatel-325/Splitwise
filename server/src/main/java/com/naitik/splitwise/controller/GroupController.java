package com.naitik.splitwise.controller;

import com.naitik.splitwise.entity.Groups;
import com.naitik.splitwise.entity.User;
import com.naitik.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
    public ResponseEntity<Groups> addGroup(@RequestBody Groups group) {
        List<User> users = group.getUsers();
        if (users != null) {
            for (User user : users) {
//                group.addUser(user);
            }
        }
        return groupService.addGroup(group);
    }

}
