package com.naitik.splitwise.requestService;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ExpanseDto {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;
    private String paidBy; // Only the name of the person who paid
    private String groupName;
    private List<String> contributors; // List of contributor names

    // Getters and Setters
}
