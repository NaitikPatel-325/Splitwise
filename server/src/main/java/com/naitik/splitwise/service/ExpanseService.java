package com.naitik.splitwise.service;

import com.naitik.splitwise.daojpa.ExpansesDao;
import com.naitik.splitwise.entity.Expanse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpanseService {

    @Autowired
    private ExpansesDao expansesDao;

    public Expanse createExpense(Expanse expanse) {

        return expansesDao.save(expanse);
    }

    public List<Expanse> getAllExpensesForGroup(Long groupId) {

        return expansesDao.findAllByGroupId(groupId);
    }

    public Expanse getExpenseById(Long expenseId) {

        return expansesDao.findById(expenseId).orElse(null);
    }

    public Double getTotalExpensesForGroup(Long groupId) {
        System.out.println("Error in updating expense");
        List<Expanse> expenses = expansesDao.findAllByGroupId(groupId);
        Double totalExpenses = expenses.stream().mapToDouble(Expanse::getAmount).sum();
        return totalExpenses;
    }

    public Expanse updateExpense(Long expenseId, Expanse updatedExpanse) {
        Expanse existingExpense = expansesDao.findById(expenseId).orElse(null);
        if (existingExpense != null) {
            updatedExpanse.setId(expenseId);
            return expansesDao.save(updatedExpanse);
        }
        return null;
    }

    public void deleteExpense(Long expenseId) {
        expansesDao.deleteById(expenseId);
    }

}
