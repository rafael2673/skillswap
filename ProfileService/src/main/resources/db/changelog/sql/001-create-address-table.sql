-- liquibase formatted sql

-- changeset Rafael:001
CREATE TABLE address (
    address_id SERIAL PRIMARY KEY NOT NULL,
    street VARCHAR(100),
    number VARCHAR(10),
    zip_code varchar(30),
    state VARCHAR(50),
    country VARCHAR(50)
);

-- rollback DROP TABLE address