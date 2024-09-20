package com.naitik.splitwise.requestService;

import lombok.Data;

import java.util.List;

@Data
public class GroupRequest {

    private String groupName;
    private String currency;
    private List<String> participants;
    private String useremail;
}
