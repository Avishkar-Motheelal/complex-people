package com.example.complexpeople.dto;

import com.example.complexpeople.model.Role;
import lombok.Data;

@Data
public class RoleDTO {
    private String roleName;


    public RoleDTO(Role role) {
        this.roleName = role.getRoleName();
    }
}
