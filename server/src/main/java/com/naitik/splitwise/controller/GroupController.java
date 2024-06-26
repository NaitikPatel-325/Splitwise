package com.naitik.splitwise.controller;

import com.naitik.splitwise.daojpa.ExpansesDao;
import com.naitik.splitwise.entity.Expanse;
import com.naitik.splitwise.entity.Groups;
import com.naitik.splitwise.entity.User;
import com.naitik.splitwise.payLoad.Request.GroupRequest;
import com.naitik.splitwise.payLoad.Response.MessageResponse;
import com.naitik.splitwise.security.services.UserDetailsImpl;
import com.naitik.splitwise.service.ExpanseService;
import com.naitik.splitwise.service.GroupService;
import com.naitik.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ExpanseService expanseService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> addGroup(@RequestHeader("Authorization") String request, @RequestBody GroupRequest group) {
        System.out.println("GroupController.addGroup");

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Unauthorized access"));
        }

        Long userId = userDetails.getId();

        if (userId == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User ID not found"));
        }

        User user = userService.getUserById(userId);

        if (user == null) {
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
                return ResponseEntity.notFound().build();
            }
        }

        groupService.addGroup(s);
        return ResponseEntity.ok(new MessageResponse("Group added successfully"));
    }


    @GetMapping("/get")//groups of user
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getGroups(@RequestHeader("Authorization") String request) {
        System.out.println("GroupController.getGroups");

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(groupService.getGroups(user));
    }



    @GetMapping("/member")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getGroupMembers(@RequestHeader("Authorization") String request, @RequestParam int id) {
        System.out.println("GroupController.getGroupMembers");

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        User user = userService.getUserById(userId);
        System.out.println("GroupController.getGroupMembers: user = " + user);
        return ResponseEntity.ok(groupService.getGroupMembers(user, id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteGroup(@RequestHeader("Authorization") String request, @PathVariable int id) {
        System.out.println("GroupController.deleteGroup");

        Groups gp = groupService.getGroupByIds(id);

        List<User> users = gp.getUsers();
        for (User u : users) {
            u.getGroups().remove(gp);
            userService.saveUser(u);
        }

        List<Expanse> expanse = expanseService.getExpanseByGroupId(id);

        for (Expanse e : expanse) {
            expanseService.deleteExpense(e.getId(), e.getContributors());
        }

        groupService.deleteGroup(id);

        return ResponseEntity.ok(new MessageResponse("Group deleted successfully"));
    }

}
