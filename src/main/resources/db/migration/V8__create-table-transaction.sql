CREATE TYPE transaction_type_enum AS ENUM('entry','out');

CREATE TABLE transaction (
    transaction_id SERIAL PRIMARY KEY,
    transaction_stock_product_fk INT NOT NULL,
    transaction_qty INT NOT NULL,
    transaction_type transaction_type_enum NOT NULL,
    FOREIGN KEY (transaction_stock_product_fk) REFERENCES stock_product(stock_product_id) ON DELETE CASCADE
);