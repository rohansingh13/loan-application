package com.example.loanapplicationbackend.service;

import com.example.loanapplicationbackend.model.Role;
import com.example.loanapplicationbackend.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role creatNewRole(Role role) {
        return  roleRepository.save(role);
    }
}
