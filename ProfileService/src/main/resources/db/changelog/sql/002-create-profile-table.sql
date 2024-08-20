-- liquibase formatted sql

-- changeset Rafael:002
CREATE TABLE profile (
   profile_id SERIAL PRIMARY KEY,
   first_name VARCHAR(50) NOT NULL,
   bio TEXT,
   picture_url TEXT,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   address_id INTEGER
);

-- rollback DROP TABLE profile