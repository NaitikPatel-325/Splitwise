package com.naitik.splitwise.controller;

import com.naitik.splitwise.entity.Expanse;
import com.naitik.splitwise.entity.Groups;
import com.naitik.splitwise.entity.User;
import com.naitik.splitwise.payLoad.Request.ExpansesRequest;
import com.naitik.splitwise.service.ExpanseService;
import com.naitik.splitwise.service.GroupService;
import com.naitik.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
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
    public ResponseEntity<Expanse> createExpense( @RequestHeader("Authorization") String request,@RequestBody ExpansesRequest expanse) {
        if(expanse == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        else if (userService.getUserByUsername(expanse.getPaidBy()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        else if (GroupService.getGroupByName(expanse.getGroup()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User ur = userService.getUserByUsername(expanse.getPaidBy());
        Groups gr = GroupService.getGroupByName(expanse.getGroup());
        Expanse e = new Expanse(expanse.getDescription(), expanse.getAmount(), expanse.getDate(),ur, gr);
        Expanse createdExpense = expanseService.createExpense(e);
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



    @GetMapping("/{expenseId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Expanse> getExpenseDetails(@RequestHeader("Authorization") String request,@PathVariable Long expenseId) {

        Expanse expense = expanseService.getExpenseById(expenseId);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @PostMapping("/update/{expenseId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

    public ResponseEntity<Expanse> updateExpense(@RequestHeader("Authorization") String request,@PathVariable Long expenseId, @RequestBody ExpansesRequest updatedExpanse) {
        if(updatedExpanse == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        else if (userService.getUserByUsername(updatedExpanse.getPaidBy()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        else if (GroupService.getGroupByName(updatedExpanse.getGroup()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User UR = userService.getUserByUsername(updatedExpanse.getPaidBy());
        Groups GR = GroupService.getGroupByName(updatedExpanse.getGroup());
        Expanse UE = new Expanse(updatedExpanse.getDescription(), updatedExpanse.getAmount(), updatedExpanse.getDate(),UR,GR);
        Expanse updatedExpense = expanseService.updateExpense(expenseId, UE);
        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    }

    @GetMapping("/group/{groupId}/expensesPerUser")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Double> getExpensesPerUserInGroup(@RequestHeader("Authorization") String request, @PathVariable Long groupId) {
        boolean group = GroupService.getGroupById(groupId);
        if (group ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Double groupExpenses = expanseService.getTotalExpensesForGroup(groupId);




        List<User> groupMembers = GroupService.getGroupMember(groupId);
        int numberOfUsers = groupMembers.size();

        if (numberOfUsers == 0) {
            return new ResponseEntity<>(0.0, HttpStatus.OK);
        }

        double expensesPerUser = groupExpenses / numberOfUsers;
        return new ResponseEntity<>(expensesPerUser, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{expenseId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteExpense(@RequestHeader("Authorization") String request,@PathVariable Long expenseId) {
        expanseService.deleteExpense(expenseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
//{
//        "description": "Dinners",
//        "amount": 50.0,
//        "date": "2024-03-25",
//        "paidBy": "Naitik",
//        "group": "jaipur"
//        }
