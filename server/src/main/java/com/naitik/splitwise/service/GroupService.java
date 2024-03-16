package com.naitik.splitwise.service;

import com.naitik.splitwise.daojpa.GroupDao;
import com.naitik.splitwise.entity.Groups;
import com.naitik.splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
//        public ResponseEntity<Groups> getGroup(int id) {
//            try {
//                Groups group = groupDao.findById(id);
//                if (group != null) {
//                    return new ResponseEntity<>(group, HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//        }

//        public ResponseEntity<Groups> addUserToGroup(int groupId, int userId) {
//            try {
//                Groups group = groupDao.findById(groupId);
//                User user = userDao.findById(userId);
//                if (group != null && user != null) {
//                    group.getUsers().add(user);
//                    user.getGroups().add(group);
//                    groupDao.save(group);
//                    userDao.save(user);
//                    return new ResponseEntity<>(group, HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//        }

//        public ResponseEntity<Group> removeUserFromGroup(int groupId, int userId) {
//            try {
//                Group group = groupDao.findById(groupId);
//                User user = userDao.findById(userId);
//                if (group != null && user != null) {
//                    group.getUsers().remove(user);
//                    user.getGroups().remove(group);
//                    groupDao.save(group);
//                    userDao.save(user);
//                    return new ResponseEntity<>(group, HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//        }

//        public ResponseEntity<Groups> deleteGroup(int id) {
//            try {
//                groupDao.deleteById(id);
//                return new ResponseEntity<>(HttpStatus.OK);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//        }
//
//        public ResponseEntity<List<Groups>> getAllGroups() {
//            try {
//                List<Groups> groups = groupDao.findAll();
//                return new ResponseEntity<>(groups, HttpStatus.OK);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//        }

