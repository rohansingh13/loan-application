package com.example.loanapplicationbackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
public class HomeControllerTest {

    private HomeController homeController;

    @BeforeEach
    public void setUp() {
        homeController = new HomeController();
    }

    @Test
    public void testHello() {
        ResponseEntity<String> response = homeController.hello();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World", response.getBody());
    }
}
