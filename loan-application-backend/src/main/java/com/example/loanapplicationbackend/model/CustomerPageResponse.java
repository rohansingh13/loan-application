package com.example.loanapplicationbackend.model;

import java.util.List;

public class CustomerPageResponse {

    private List<Customer> customers;
    private long totalCustomers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
}
