CREATE TABLE stock (
    stock_id SERIAL PRIMARY KEY,
    stock_name VARCHAR(20) NOT NULL,
    stock_company_fk INT NOT NULL,
    FOREIGN KEY (stock_company_fk) REFERENCES company(company_id) ON DELETE CASCADE
);