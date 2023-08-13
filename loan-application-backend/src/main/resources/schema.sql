CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL
);


CREATE SEQUENCE customer_ssn_seq START WITH 10001;

CREATE TABLE customer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          customer_ssn INT NOT NULL DEFAULT NEXTVAL('customer_ssn_seq'),
                          full_name VARCHAR(255) NOT NULL,
                          loan_amount DOUBLE NOT NULL,
                          equity_amount DOUBLE NOT NULL,
                          salary_amount DOUBLE NOT NULL
);


