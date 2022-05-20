package com.example.insuranceprototype.Auth.security.services;

import com.example.insuranceprototype.Auth.models.User;
import com.example.insuranceprototype.Auth.models.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDTO listUser(User user){

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRoleId(user.getRoleId());
        dto.setAgentId(user.getAgentId());
        dto.setRole(user.getRole());
        return dto;
    }


}
