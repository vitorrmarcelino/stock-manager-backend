CREATE TABLE company (
    company_id SERIAL PRIMARY KEY,
    company_name VARCHAR(50) NOT NULL UNIQUE,
    company_cnpj VARCHAR(14) NOT NULL UNIQUE,
    company_user_fk INT NOT NULL UNIQUE,
    FOREIGN KEY (company_user_fk) REFERENCES "user"(user_id) ON DELETE CASCADE
);