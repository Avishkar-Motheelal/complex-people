package com.example.complexpeople.dto;

import com.example.complexpeople.model.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    @NotNull
    @Pattern(regexp = "^(staff|resident|visitor)$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "User type must be 'staff', 'resident' or 'visitor'")
    private String roleType;

    public static Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setType(roleDTO.getRoleType());
        return role;
    }
}
