package com.example.loanapplicationbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private int customerSsn;

    private String fullName;

    private double loanAmount;

    private double equityAmount;

    private double salaryAmount;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCustomerSsn() {
        return customerSsn;
    }

    public void setCustomerSsn(int customerSsn) {
        this.customerSsn = customerSsn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getEquityAmount() {
        return equityAmount;
    }

    public void setEquityAmount(double equityAmount) {
        this.equityAmount = equityAmount;
    }

    public double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }
}

