CREATE TABLE customer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          customer_ssn INT AUTO_INCREMENT NOT NULL,
                          full_name VARCHAR(255) NOT NULL,
                          loan_amount DOUBLE NOT NULL,
                          equity_amount DOUBLE NOT NULL,
                          salary_amount DOUBLE NOT NULL
);