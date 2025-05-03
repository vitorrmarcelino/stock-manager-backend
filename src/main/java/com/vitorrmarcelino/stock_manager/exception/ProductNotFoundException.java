package com.vitorrmarcelino.stock_manager.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(){super("Product not found");}

    public ProductNotFoundException(String message) {
        super(message);
    }
}
