package com.naitik.splitwise.controller;
import com.naitik.splitwise.requestService.ExpanseRequest;
import com.naitik.splitwise.requestService.ExpanseDto;

import com.naitik.splitwise.service.ExpanseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.naitik.splitwise.entity.Expanse;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ExpanseController {

    @Autowired
    private ExpanseService expanseService;

    @PostMapping("/expanses")
    public ResponseEntity<String> addExpanse(@RequestBody ExpanseRequest expanseDTO) {
        try {
            System.out.println("expanse");
            expanseService.addExpanse(expanseDTO);
            return ResponseEntity.ok("Expanse created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating expanse: " + e.getMessage());
        }
    }



    @GetMapping("/GetAllExpenses/{id}")
    public List<ExpanseDto> getAllExpenses(@PathVariable Long id) {
        List<Expanse> expanses = expanseService.getAllExpensesByGroupId(id);

        List<ExpanseDto> expanseDTOList = expanses.stream().map(expanse -> {
            ExpanseDto dto = new ExpanseDto();
            dto.setId(expanse.getId());
            dto.setDescription(expanse.getDescription());
            dto.setAmount(expanse.getAmount());
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(expanse.getDate(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
            dto.setDate(zonedDateTime.toLocalDate());


            if (expanse.getPaidBy() != null) {
                dto.setPaidBy(expanse.getPaidBy().getFullname());
            }

            if (expanse.getGroup() != null) {
                dto.setGroupName(expanse.getGroup().getGroupName());
            }

            if (expanse.getContributors() != null && !expanse.getContributors().isEmpty()) {
                List<String> contributorNames = expanse.getContributors().stream()
                        .map(contributor -> contributor.getFullname())
                        .collect(Collectors.toList());
                dto.setContributors(contributorNames);
            }

            return dto;
        }).collect(Collectors.toList());

        return expanseDTOList;
    }


    @DeleteMapping("/DeleteExpense/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        System.out.println("delete");
        try {
            expanseService.deleteExpanse(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/GetPerUser/{groupId}")
    public ResponseEntity<List<Map<String, Object>>> getExpensesPerUser(@PathVariable Long groupId) {
        return ResponseEntity.ok(expanseService.getExpensesPerUser(groupId));
    }

}
