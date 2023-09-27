package com.example.loanapplicationbackend.controller;

import com.example.loanapplicationbackend.model.Customer;
import com.example.loanapplicationbackend.model.CustomerPageResponse;
import com.example.loanapplicationbackend.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
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
        Page<Customer> mockCustomerPage = new PageImpl<>(Arrays.asList(
                new Customer(12345, "Rohan Test", 100000, 50000, 60000),
                new Customer(45678, "Sachin Test", 120000, 60000, 70000)
        ));

        // Mock the service method to return the mock Page
        when(customerService.getAllCustomers(anyInt(), anyInt())).thenReturn(mockCustomerPage);

        // Perform the test
        ResponseEntity<CustomerPageResponse> response = customerController.getAllCustomers(0, 10);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the returned CustomerPageResponse contains the expected values
        CustomerPageResponse customerPageResponse = response.getBody();
        assertNotNull(customerPageResponse);
        assertEquals(mockCustomerPage.getContent(), customerPageResponse.getCustomers());
        assertEquals(mockCustomerPage.getTotalElements(), customerPageResponse.getTotalCustomers());

        // Verify that the service method was called with the correct parameters
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
