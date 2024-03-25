package com.naitik.splitwise.controller;

import com.naitik.splitwise.entity.Groups;
import com.naitik.splitwise.entity.User;
import com.naitik.splitwise.payLoad.Request.GroupRequest;
import com.naitik.splitwise.payLoad.Response.MessageResponse;
import com.naitik.splitwise.security.services.UserDetailsImpl;
import com.naitik.splitwise.service.GroupService;
import com.naitik.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> addGroup(@RequestHeader("Authorization") String request, @RequestBody GroupRequest group) {
        System.out.println("GroupController.addGroup");

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails == null) {
            // Handle the case where UserDetails is null
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Unauthorized access"));
        }

        Long userId = userDetails.getId();

        if (userId == null) {
            // Handle the case where userId is null
            return ResponseEntity.badRequest().body(new MessageResponse("User ID not found"));
        }

        User user = userService.getUserById(userId);

        if (user == null) {
            // Handle the case where user is not found
            return ResponseEntity.notFound().build();
        }

        Groups s = new Groups(group.getGroupName(), group.getCurrency());
        groupService.addUserToGroup(user, s);

        for (String u : group.getParticipants()) {
            if (u.equals(user.getUsername())) {
                continue;
            }
            System.out.println("GroupController.addGroup: user = " + u);
            User user1 = userService.getUserByUsername(u);
            if (user1 != null) {
                groupService.addUserToGroup(user1, s);
            } else {
                // Handle the case where user1 is not found
                return ResponseEntity.notFound().build();
            }
        }

        groupService.addGroup(s);
        return ResponseEntity.ok(new MessageResponse("Group added successfully"));
    }


    @GetMapping("/get")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getGroups(@RequestHeader("Authorization") String request) {
        System.out.println("GroupController.getGroups");

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(groupService.getGroups(user));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getGroup(@RequestHeader("Authorization") String request, @PathVariable int id) {
        System.out.println("GroupController.getGroup");

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(groupService.getGroup(user, id));
    }
}
