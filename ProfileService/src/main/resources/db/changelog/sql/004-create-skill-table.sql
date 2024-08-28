-- liquibase formatted sql

-- changeset Rafael:005
CREATE TABLE skill (
    skill_id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50),
    description TEXT
);

-- rollback DROP TABLE skill
