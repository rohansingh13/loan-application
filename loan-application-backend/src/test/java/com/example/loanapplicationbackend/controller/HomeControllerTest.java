package com.example.loanapplicationbackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
