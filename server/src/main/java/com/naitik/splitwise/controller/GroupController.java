package com.naitik.splitwise.controller;

import com.naitik.splitwise.entity.Group;
import com.naitik.splitwise.requestService.GroupRequest;
import com.naitik.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
    public ResponseEntity<String> createGroup(@RequestBody GroupRequest groupRequest) {
        try {
            // Log the data
            Group group = new Group();
            group.setGroupName(groupRequest.getGroupName());
            group.setCurrency(groupRequest.getCurrency());

//            System.out.println(groupRequest.getUseremail());
            List<String> participants = groupRequest.getParticipants();
            if (!participants.contains(groupRequest.getUseremail())) {
                participants.add(groupRequest.getUseremail());
            }
//            System.out.println(participants);

            groupService.createGroup(group,participants);

            return ResponseEntity.ok("Group created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while creating group: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public List<Group> getGroupsByUserEmail(@RequestParam String email) {
        return groupService.findGroupsByUserEmail(email);
    }

    @GetMapping("/member")
    public ResponseEntity<?> getGroupMembers(@RequestParam Long id) {
        Optional<Group> groupOptional = groupService.findGroupById(id);
        if (groupOptional.isPresent()) {
            Group group = groupOptional.get();
            return ResponseEntity.ok(group.getUsers());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        try {
            System.out.println("delete group");
            boolean isDeleted = groupService.deleteGroup(groupId);
            if (isDeleted) {
                return ResponseEntity.ok("Group deleted successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while deleting group: " + e.getMessage());
        }
    }


}
