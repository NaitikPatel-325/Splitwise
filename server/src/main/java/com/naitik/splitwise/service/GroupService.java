package com.naitik.splitwise.service;

import com.naitik.splitwise.daojpa.GroupDao;
import com.naitik.splitwise.entity.Groups;
import com.naitik.splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupDao groupDao;


    public ResponseEntity<Groups> addGroup(Groups group) {
        try {
            Groups savedGroup = groupDao.save(group);
            return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in adding group");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public void addUserToGroup(User user, Groups group) {
        if (group.getUsers() == null)
            group.setUsers(new ArrayList<>());
        if (user.getGroups() == null)
            user.setGroups(new ArrayList<>());
        group.getUsers().add(user);
        user.getGroups().add(group);
        groupDao.save(group);
    }

    public List<Groups> getGroups(User user) {
        return user.getGroups();
    }



    public Object getGroupMembers(User user, int id) {
        for (Groups group : user.getGroups()) {
            if (group.getId() == id) {
                return group.getUsers();
            }
        }
        return null;
    }

    public Groups getGroupByIds(int groupId) {
        return groupDao.findById(groupId).orElse(null);
    }

    public Object deleteGroup(int id) {
        groupDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}