SELECT * FROM customers WHERE customerNumber = 175; 
EXPLAIN SELECT * FROM customers WHERE customerNumber = 175;
EXPLAIN SELECT * FROM customers WHERE customerNumber = 175; 
EXPLAIN SELECT * FROM customers WHERE contactFirstName = 'Jean' or contactFirstName = 'King';
ALTER TABLE customers DROP INDEX idx_full_name;