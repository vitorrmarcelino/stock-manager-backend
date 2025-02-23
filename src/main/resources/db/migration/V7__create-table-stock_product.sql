CREATE TABLE stock_product (
    stock_product_id SERIAL PRIMARY KEY,
    stock_product_product_fk INT NOT NULL,
    stock_product_stock_fk INT NOT NULL,
    FOREIGN KEY (stock_product_product_fk) REFERENCES product(product_id) ON DElETE CASCADE,
    FOREIGN KEY (stock_product_stock_fk) REFERENCES stock(stock_id) ON DELETE CASCADE
);