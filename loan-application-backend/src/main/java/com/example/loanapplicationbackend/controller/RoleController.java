package com.example.loanapplicationbackend.controller;

import com.example.loanapplicationbackend.model.Role;
import com.example.loanapplicationbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping({"/createNewRole"})
    public ResponseEntity<Role> createNewRole(@RequestBody Role role) {
        Role savedRole = roleService.creatNewRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }
}
