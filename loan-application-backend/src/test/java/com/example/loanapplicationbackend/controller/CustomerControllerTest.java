package com.example.loanapplicationbackend.controller;

import com.example.loanapplicationbackend.model.Customer;
import com.example.loanapplicationbackend.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> mockCustomers  = new ArrayList<>();
        mockCustomers .add(new Customer(12345, "Rohan Test", 100000, 50000, 60000));
        mockCustomers .add(new Customer(45678, "Sachin Test", 120000, 60000, 70000));

        when(customerService.getAllCustomers(anyInt(), anyInt())).thenReturn(mockCustomers );

        when(customerService.getAllCustomers(anyInt(), anyInt())).thenReturn(mockCustomers);

        // Perform the test
        ResponseEntity<List<Customer>> response = customerController.getAllCustomers(0, 10);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCustomers, response.getBody());
        verify(customerService, times(1)).getAllCustomers(0, 10);
    }

    @Test
    public void testSaveCustomer() throws Exception {
        Customer newCustomer = new Customer(12546, "Rohan Test", 1500, 800, 2500);
        Customer savedCustomer = new Customer(58467, "Sachin Test", 1500, 800, 2500);
        when(customerService.saveCustomer(any(Customer.class))).thenReturn(savedCustomer);

        ResponseEntity<Customer> response = customerController.saveCustomer(newCustomer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedCustomer, response.getBody());
        verify(customerService, times(1)).saveCustomer(newCustomer);
    }

}
