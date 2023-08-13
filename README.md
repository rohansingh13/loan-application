
# Customer Loan Application

Welcome to the Customer Loan Application project! This application allows customers to apply for a real estate loan.



## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [Tests](#tests)
- [Monitoring](#monitoring)
- [API Documentation](#api-documentation)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)

## Overview

The Loan Application is a web-based application built using Spring Boot for the backend and Angular for the frontend. It provides endpoints to manage customer data and perform loan-related operations. The application uses JWT-based authentication to secure the API endpoints. The application uses H2 database for development.

## Prerequisites

Before running the application, ensure you have the following installed:

- Java 11 or higher
- Node.js and npm
- Angular CLI
- Apache Maven
- H2 Database (optional, for development)
- Git
- Your preferred IDE (e.g., IntelliJ IDEA, Eclipse)

## Getting Started

To get started with the application, follow these steps:

1. Clone the Git repository: `git clone https://github.com/rohansingh13/loan-application.git`
2. Change directory to the backend folder: `cd loan-application-backend`
3. Build the backend application: `./mvnw clean package`
4. Change directory to the frontend folder: `cd ../loan-application-frontend`
5. Install frontend dependencies: `npm install`

## Running the Application

To run the backend application, use the following command:

```bash
  java -jar loan-application-backend/target/loan-application-backend-0.0.1-SNAPSHOT.jar
```

To run the frontend application, use the following command:

```bash
  ng serve
```

The backend will be accessible at `http://localhost:9090` and the frontend at `http://localhost:4200`.

##Testing through Postman
We can test the backend using Postman as below:

1. Admin user is created on server startup.
2. Make sure to execute the sql statements in schema.sql and data.sql kept under resources.
3. Then we can test the various endpoints with request:

i. Login Endpoint:
- http://localhost:9090/rest/auth/login
- Endpoint: POST /login

Request Body:
{
    "username": "admin",
    "password": "admin"
}

ii. Signup Endpoint:
-http://localhost:9090/rest/auth/signup

Request Body:
{
    "username":"user3",
    "password":"user123"
}

iii. Use the token generated in the above request to call the other below endpoints:

- http://localhost:9090/rest/home
- http://localhost:9090/api/v1/show-customers?page=0&size=10
- http://localhost:9090/api/v1/save-customer

Request for save-customer:

{
    "fullName": "Rohan Testsuser",
    "loanAmount": 90000,
    "equityAmount": 40457,
    "salaryAmount": 1000000        
}



## Tests

The application includes both unit tests and integration tests.

To run the backend tests, use the following command:

```bash
  ./mvnw test
```

## Monitoring

The application provides basic monitoring using Spring Boot Actuator. The Actuator endpoints can be accessed at `http://localhost:9090/actuator`.


## API Documentation

The API documentation is built using Swagger. It includes details about the available endpoints, request formats, and response formats. To access the documentation we can use the below link

`http://localhost:9090/swagger-ui/`

## Documentation for deployment using cloud services

There are 2 approaches that I have looked into for deployment using AWS cloud services.

1. AWS EC2
- The documentation for deployment of the application on AWS using AWS EC2 can be found in AWS_EC2_DEPLOYMENT.md file in the git root path.
- I have also deployed a spring boot application on cloud using EC2 and the same can be accessed at :
- http://ec2-16-171-206-111.eu-north-1.compute.amazonaws.com:9098/

- The steps for deploying the spring boot application using AWS EC2 with snapshots can be found in the word file 
- File: Steps_to_deploy_the_Spring_Boot_application_on_AWS_using_AWS_EC2.docx in the root directory.

2. AWS Elastic BeanStalk
- The documentation for deployment of the application on AWS using AWS Elastic BeanStalk can be found in DEPLOYMENTAWS.md file in the git root path.

## Technologies Used

- Spring Boot
- Angular
- JSON Web Tokens (JWT) for authentication
- H2 in-memory database (for development)
- PostgreSQL/MySQL (for production)
- Spring Boot Actuator for monitoring

## Contributing

If you want to contribute to this project, please follow the standard Git workflow:

1. Fork the repository
2. Create a new branch for your feature or bug fix
3. Make your changes and commit them
4. Push your changes to your fork
5. Submit a pull request to the main repository



