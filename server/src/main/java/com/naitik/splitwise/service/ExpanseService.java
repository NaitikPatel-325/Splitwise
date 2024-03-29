package com.naitik.splitwise.service;

import com.naitik.splitwise.daojpa.ExpansesDao;
import com.naitik.splitwise.entity.Expanse;
import com.naitik.splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpanseService {

    @Autowired
    private ExpansesDao expansesDao;

    @Autowired
    private UserService userService;

    public Expanse createExpense(Expanse expanse) {

        return expansesDao.save(expanse);
    }

    public List<Expanse> getAllExpensesForGroup(Long groupId) {

        return expansesDao.findAllByGroupId(groupId);
    }



    public Double getTotalExpensesForGroup(Long groupId) {
        System.out.println("Error in updating expense");
        List<Expanse> expenses = expansesDao.findAllByGroupId(groupId);
        Double totalExpenses = expenses.stream().mapToDouble(Expanse::getAmount).sum();
        return totalExpenses;
    }


    public void deleteExpense(Long expenseId,List<User> contributors) {
        for (User contributor : contributors) {
            contributor.getContributedExpanses().removeIf(e -> e.getId().equals(expenseId));
            userService.saveUser(contributor);
        }
        expansesDao.deleteById(expenseId);
    }

    public List<User> getContributors(Long expenseId) {
        Expanse expanse = expansesDao.findById(expenseId).orElse(null);
        return expanse.getContributors();
    }

    public List<Expanse> getExpanseByGroupId(int id) {
        return expansesDao.findAllByGroupId((long) id);
    }
}
