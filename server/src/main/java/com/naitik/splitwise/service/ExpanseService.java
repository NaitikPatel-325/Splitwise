package com.naitik.splitwise.service;

import com.naitik.splitwise.entity.Expanse;
import com.naitik.splitwise.dao.ExpanseDao;
import com.naitik.splitwise.dao.GroupDao;
import com.naitik.splitwise.dao.UserDAO;
import com.naitik.splitwise.entity.User;
import com.naitik.splitwise.requestService.ExpanseRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
public class ExpanseService {

    @Autowired
    private ExpanseDao expanseRepository;

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private GroupDao groupRepository;

    public Expanse addExpanse(ExpanseRequest expanseDTO) {
        Expanse expanse = new Expanse();
        expanse.setDescription(expanseDTO.getDescription());
        expanse.setAmount(expanseDTO.getAmount());
        expanse.setDate(expanseDTO.getDate());
        expanse.setGroup(groupRepository.getReferenceById(expanseDTO.getId()));
        System.out.println(expanse);
        User paidBy = userRepository.findByEmail(expanseDTO.getUserEmail());
        expanse.setPaidBy(paidBy);

        for (Integer userId : expanseDTO.getUsers()) {
            User contributor = userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new RuntimeException("Contributor not found with ID: " + userId));
            expanse.getContributors().add(contributor);

            contributor.getContributedExpanses().add(expanse);
        }

        return expanseRepository.save(expanse);
    }

    public void deleteExpanse(Long expanseId) {
        Expanse expanse = expanseRepository.findById(expanseId)
                .orElseThrow(() -> new EntityNotFoundException("Expanse not found"));

        for (User contributor : expanse.getContributors()) {
            contributor.getContributedExpanses().remove(expanse);
        }

        if (expanse.getGroup() != null) {
            expanse.getGroup().getExpanses().remove(expanse);
        }

        expanseRepository.delete(expanse);
    }



    public List<Expanse> getAllExpensesByGroupId(Long userId) {
        return expanseRepository.findByGroupId(userId);
    }

    public List<Map<String, Object>> getExpensesPerUser(Long groupId) {
        List<Expanse> expanses = expanseRepository.findByGroupId(groupId);

        Map<String, Double> userExpenses = new HashMap<>();

        for (Expanse expanse : expanses) {
            double amount = expanse.getAmount();
            List<User> contributors = expanse.getContributors();

            if (contributors != null && !contributors.isEmpty()) {
                double splitAmount = amount / contributors.size();

                for (User contributor : contributors) {
                    userExpenses.merge(contributor.getFullname(), splitAmount, Double::sum);
                }
            }
        }

        return userExpenses.entrySet()
                .stream()
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("username", entry.getKey());
                    result.put("amount", entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());
    }


}
