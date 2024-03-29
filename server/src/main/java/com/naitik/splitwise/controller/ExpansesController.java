package com.naitik.splitwise.controller;

import com.naitik.splitwise.entity.Expanse;
import com.naitik.splitwise.entity.Groups;
import com.naitik.splitwise.entity.User;
import com.naitik.splitwise.payLoad.Request.ExpansesRequest;
import com.naitik.splitwise.security.services.UserDetailsImpl;
import com.naitik.splitwise.service.ExpanseService;
import com.naitik.splitwise.service.GroupService;
import com.naitik.splitwise.service.UserService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/group/expanses")
public class ExpansesController {

    @Autowired
    private GroupService GroupService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExpanseService expanseService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Expanse> createExpense(@RequestHeader("Authorization") String request, @RequestBody ExpansesRequest expanse) {
        System.out.println("Creating expense" + expanse);
        if (expanse == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        User ur = userService.getUserById(userId);
        Groups gr = GroupService.getGroupByIds(expanse.getId());

        List<Integer> userIds = expanse.getUsers();
        List<User> contributors = new ArrayList<>();
        for (Integer u : userIds) {
            if (u.equals(userId)) {
                continue;
            }
            User user = userService.getUserById((long) u);
            if (user != null) {
                contributors.add(user);
            }
        }

        Expanse e = new Expanse(expanse.getDescription(), expanse.getAmount(), expanse.getDate(), ur, gr, contributors);

        Expanse createdExpense = expanseService.createExpense(e);

        for (User contributor : contributors) {
            contributor.getContributedExpanses().add(createdExpense);
            userService.saveUser(contributor);
        }

        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @GetMapping("/details/{groupId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Double> getTotalExpensesForGroup(@RequestHeader("Authorization") String request, @PathVariable String groupId) {
        try {
            Long groupIdLong = Long.parseLong(groupId);
            System.out.println("Total expense"+groupIdLong);
            Double totalExpenses = expanseService.getTotalExpensesForGroup(groupIdLong);
            System.out.println("Total expense"+totalExpenses);
            return new ResponseEntity<>(totalExpenses, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/GetAllExpenses/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Expanse>> getExpenseDetails(@RequestHeader("Authorization") String request,@PathVariable int id) {
        System.out.println("Getting all expenses for group" + id);
        List<Expanse> expenses = expanseService.getAllExpensesForGroup((long)id);
        for (Expanse e : expenses) {
            System.out.println(e.getPaidBy().getId() );
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }


    @DeleteMapping("/DeleteExpense/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteExpense(@RequestHeader("Authorization") String request, @PathVariable String id) {
        try {
            Long expenseId = Long.parseLong(id);
            List<User> contributors = expanseService.getContributors(expenseId);

            expanseService.deleteExpense(expenseId,contributors);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
