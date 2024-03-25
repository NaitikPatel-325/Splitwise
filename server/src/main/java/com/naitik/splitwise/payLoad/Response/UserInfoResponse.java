package com.naitik.splitwise.payLoad.Response;

import com.naitik.splitwise.entity.Groups;
import com.naitik.splitwise.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
public class UserInfoResponse {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private List<Groups> groups;
    private String jwtToken;

    public UserInfoResponse( String jwtToken,Long id, String username, String email, List<Groups> groups) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.groups = groups;
        this.jwtToken = jwtToken;
    }

    public UserInfoResponse( String jwtToken,Long id, String username, String email, List<String> roles, List<Groups> groups) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.groups = groups;
        this.jwtToken = jwtToken;
    }

}
