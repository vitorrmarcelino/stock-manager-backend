package com.vitorrmarcelino.stock_manager.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(){super("Stock not found");}

    public StockNotFoundException(String message) {
        super(message);
    }
}
