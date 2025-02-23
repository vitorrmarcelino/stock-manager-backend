CREATE TABLE stock_employee (
    stock_employee_id SERIAL PRIMARY KEY,
    stock_employee_employee_fk INT NOT NULL,
    stock_employee_stock_fk INT NOT NULL,
    FOREIGN KEY (stock_employee_employee_fk) REFERENCES employee(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (stock_employee_stock_fk) REFERENCES stock(stock_id) ON DELETE CASCADE
);