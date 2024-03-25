package com.naitik.splitwise.controller;

import com.naitik.splitwise.entity.Groups;
import com.naitik.splitwise.payLoad.Response.MessageResponse;
import com.naitik.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> addGroup(@RequestHeader("Authorization") String request, @RequestBody Groups group) {
        System.out.println("GroupController.addGroup");
        groupService.addGroup( group);
        return ResponseEntity.ok(new MessageResponse("Group added successfully"));
    }
}
