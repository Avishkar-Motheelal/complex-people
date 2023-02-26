package com.example.complexpeople.repository;

import com.example.complexpeople.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
//    Optional<Role> findByRoleNameIgnoreCase(String roleName);
}
