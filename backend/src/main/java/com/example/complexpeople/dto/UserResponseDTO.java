package com.example.complexpeople.dto;

import com.example.complexpeople.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private RoleDTO role;
    private ContactDetailDTO contactDetail;


    public UserResponseDTO(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = new RoleDTO(user.getRole());
        this.contactDetail = new ContactDetailDTO(user.getContactDetail());
    }


    public static List<UserResponseDTO> fromUserList(List<User> users) {
        return users.stream().map(UserResponseDTO::new).toList();
    }
}
