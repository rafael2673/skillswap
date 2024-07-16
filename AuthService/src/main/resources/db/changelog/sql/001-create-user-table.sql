-- liquibase formatted sql

-- changeset Rafael:001
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP
);

-- changeset Rafael:002

ALTER TABLE IF EXISTS users ADD COLUMN IF NOT EXISTS role VARCHAR(9) NOT NULL default 'USER';