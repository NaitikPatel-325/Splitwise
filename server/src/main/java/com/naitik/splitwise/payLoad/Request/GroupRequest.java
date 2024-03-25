package com.naitik.splitwise.payLoad.Request;

import lombok.Data;

import java.util.List;

@Data
public class GroupRequest {
    private String groupName;
    private String currency;

    private List<String> participants;

    public GroupRequest() {
    }

    public GroupRequest(String groupName, String currency, List<String> participants) {
        this.groupName = groupName;
        this.currency = currency;
        this.participants= participants;
    }

}
