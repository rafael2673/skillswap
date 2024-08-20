-- liquibase formatted sql

-- changeset Rafael:001
CREATE TABLE address (
    address_id SERIAL PRIMARY KEY,
    street VARCHAR(100) NOT NULL,
    number VARCHAR(10) NOT NULL,
    zip_code varchar(30),
    state VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
);

-- rollback DROP TABLE address