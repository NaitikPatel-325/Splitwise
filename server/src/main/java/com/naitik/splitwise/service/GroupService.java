package com.naitik.splitwise.service;

import com.naitik.splitwise.dao.GroupDao;
import com.naitik.splitwise.dao.UserDAO;
import com.naitik.splitwise.entity.Group;
import com.naitik.splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupService {
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private GroupDao groupRepository;

    public void createGroup(Group group, List<String> participants) {
        Group savedGroup = groupRepository.save(group);

        for (String email : participants) {
            User user = userRepository.findByEmail(email);
            System.out.println(user);
            if (user != null) {
                user.getGroups().add(savedGroup);
                userRepository.save(user);
            }
        }
    }

    public Optional<Group> findGroupById(Long id) {
        return groupDao.findById(id);
    }

    public List<Group> findGroupsByUserEmail(String email) {
        return groupDao.findByUsersEmail(email);
    }

    public boolean deleteGroup(Long groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isPresent()) {
            Group group = groupOptional.get();

            group.removeUsersFromGroup();
            groupRepository.save(group);
            groupRepository.delete(group);

            return true;
        } else {
            return false;
        }
    }

}
