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

-- changeset Rafael:004
ALTER TABLE IF EXISTS address ADD COLUMN IF NOT EXISTS complement varchar(100);

-- rollback ALTER TABLE IF EXISTS address DROP COLUMN IF EXISTS complement;