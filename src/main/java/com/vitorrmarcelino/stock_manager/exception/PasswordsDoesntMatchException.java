package com.vitorrmarcelino.stock_manager.exception;

public class PasswordsDoesntMatchException extends RuntimeException {

    public PasswordsDoesntMatchException() {
        super("Password doesn't match");
    }

    public PasswordsDoesntMatchException(String message) {
        super(message);
    }
}
