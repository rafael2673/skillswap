-- liquibase formatted sql

-- changeset Rafael:077

CREATE TABLE profile_skill (
    id SERIAL PRIMARY KEY NOT NULL,
    role_in_skill VARCHAR(7),
    number VARCHAR(10),
    proficiency_level varchar(12),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profile_id INTEGER,
    address_id INTEGER,
    constraint fk_profile FOREIGN KEY (profile_id) references profile(profile_id),
    constraint fk_address FOREIGN KEY (address_id) references address(address_id)
);

-- rollback DROP TABLE address
