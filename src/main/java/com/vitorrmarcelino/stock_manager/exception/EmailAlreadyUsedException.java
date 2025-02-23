package com.vitorrmarcelino.stock_manager.exception;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException() {
        super("Email already used by another user");
    }

    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
