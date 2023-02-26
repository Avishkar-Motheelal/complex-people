package com.example.complexpeople.service;

import com.example.complexpeople.exception.NotFoundException;
import com.example.complexpeople.model.Role;
import com.example.complexpeople.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
//    private final RoleRepository roleRepository;
//
//
//    public Role getByName(String roleName) {
//        return roleRepository.findByRoleNameIgnoreCase(roleName).orElseThrow(() -> new NotFoundException("Role with specified name does not exist"));
//    }
//
//
//    public List<Role> getAll() {
//        return roleRepository.findAll();
//    }
//
//
//    public Role createOrUpdate(Role role) {
//        return roleRepository.save(role);
//    }
//
//
//    public Role getById(int id) {
//        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role with specified id not found"));
//    }
}
