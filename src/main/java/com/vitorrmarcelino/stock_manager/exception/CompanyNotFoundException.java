package com.vitorrmarcelino.stock_manager.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(){super("Company not found");}

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
