-- This file 'schema.sql' is not necessary.
-- Without this file, H2 also works.
-- schema will generated from entities automatically.
CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);
