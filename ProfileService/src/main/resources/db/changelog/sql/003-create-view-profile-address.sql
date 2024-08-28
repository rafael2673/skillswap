-- liquibase formatted sql

-- changeset Rafael:003

CREATE VIEW profile_with_address AS
SELECT p.profile_id, p.first_name, p.last_name, p.bio, p.picture_url, p.updated_at, p.user_id,
       a.address_id, a.street, a.number, a.zip_code, a.state, a.country
FROM Profile p
         JOIN Address a ON p.address_id = a.address_id;

-- rollback DROP VIEW profile_with_address