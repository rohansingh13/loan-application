DELIMITER //

CREATE TRIGGER set_customer_ssn
    BEFORE INSERT ON customer FOR EACH ROW
BEGIN
    DECLARE next_customer_ssn INT;
    SET next_customer_ssn = (
        SELECT IFNULL(MAX(customer_ssn), 100000) + 1
        FROM customer
    );
    SET NEW.customer_ssn = next_customer_ssn;
END;
//

DELIMITER ;