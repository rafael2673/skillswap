-- liquibase formatted sql

-- changeset Rafael:077

CREATE TABLE profile_skill (
    id SERIAL PRIMARY KEY NOT NULL,
    role_in_skill VARCHAR(7),
    proficiency_level varchar(12),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profile_id INTEGER,
    skill_id INTEGER,
    constraint fk_profile FOREIGN KEY (profile_id) references profile(profile_id),
    constraint fk_skill FOREIGN KEY (skill_id) references skill(skill_id)
);

-- rollback DROP TABLE profile_skill
