package com.example.loanapplicationbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rest/home")
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        logger.info("HomeController : hello " + "Home Page");
        return ResponseEntity.ok("Hello World");
    }
}