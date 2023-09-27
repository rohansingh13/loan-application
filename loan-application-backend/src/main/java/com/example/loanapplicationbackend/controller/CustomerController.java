package com.example.loanapplicationbackend.controller;

import com.example.loanapplicationbackend.model.Customer;
import com.example.loanapplicationbackend.model.CustomerPageResponse;
import com.example.loanapplicationbackend.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/show-customers")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CustomerPageResponse> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {

        Page<Customer> customerPage = customerService.getAllCustomers(page, size);
        List<Customer> customers = customerPage.getContent();
        logger.info("CustomerController : getAllCustomers : customers=" + customers);
        long totalCustomers = customerPage.getTotalElements();

        CustomerPageResponse response = new CustomerPageResponse();
        response.setCustomers(customers);
        response.setTotalCustomers(totalCustomers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save-customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }
}
