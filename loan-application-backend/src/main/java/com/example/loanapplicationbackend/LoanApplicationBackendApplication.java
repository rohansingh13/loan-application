package com.example.loanapplicationbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan
@EnableSwagger2
public class LoanApplicationBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplicationBackendApplication.class, args);
	}

}
