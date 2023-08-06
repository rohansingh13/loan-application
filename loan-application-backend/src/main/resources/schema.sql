CREATE SEQUENCE customer_ssn_seq START WITH 10001;

CREATE TABLE customer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          customer_ssn INT NOT NULL DEFAULT NEXTVAL('customer_ssn_seq'),
                          full_name VARCHAR(255) NOT NULL,
                          loan_amount DOUBLE NOT NULL,
                          equity_amount DOUBLE NOT NULL,
                          salary_amount DOUBLE NOT NULL
);


INSERT INTO customer(full_name,loan_amount,equity_amount,salary_amount) values('Johny Test',30000,20450,500000);

INSERT INTO customer(full_name,loan_amount,equity_amount,salary_amount) values('Jane Test',50000,30458,800000);

INSERT INTO customer(full_name,loan_amount,equity_amount,salary_amount) values('Sachin Test',80000,50457,900000);
