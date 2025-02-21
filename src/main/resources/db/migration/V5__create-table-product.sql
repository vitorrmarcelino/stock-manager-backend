CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    product_company_fk INT NOT NULL,
    FOREIGN KEY (product_company_fk) REFERENCES company(company_id) ON DELETE CASCADE
);