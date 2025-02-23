CREATE TABLE employee (
    employee_id SERIAL PRIMARY KEY,
    employee_name VARCHAR(50) NOT NULL,
    employee_cpf VARCHAR(11) NOT NULL,
    employee_user_fk INT NOT NULL UNIQUE,
    employee_company_fk INT NOT NULL,
    FOREIGN KEY (employee_user_fk) REFERENCES "user"(user_id) ON DELETE CASCADE,
    FOREIGN KEY (employee_company_fk) REFERENCES company(company_id) ON DELETE CASCADE
);