package com.naitik.splitwise.requestService;

import com.naitik.splitwise.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpanseRequest
{
    private Long id;
    private String description;
    private Double amount;
    private String date;
    private List<Integer> users;
    private String userEmail;
}
