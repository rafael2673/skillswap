-- liquibase formatted sql

-- changeset Rafael:002
CREATE TABLE profile (
   profile_id SERIAL PRIMARY KEY,
   first_name VARCHAR(50) NOT NULL,
   last_name VARCHAR(50),
   user_id INTEGER NOT NULL,
   bio TEXT,
   picture_url TEXT,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   address_id INTEGER,

   constraint fk_address_id FOREIGN KEY (address_id) references address(address_id)

);

-- rollback DROP TABLE profile