package com.example.insuranceprototype.Auth.models;

import com.example.insuranceprototype.Entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;
    private String username;
    private Long agentId;
    private Long roleId;
    private Role role;

}
