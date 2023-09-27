package com.example.loanapplicationbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/rest/home")
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @GetMapping
    public ResponseEntity<String> hello() {
        logger.info("HomeController : hello " + "Home Page");
        return ResponseEntity.ok("Hello World");
    }
}