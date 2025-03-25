package com.vitorrmarcelino.stock_manager.exception;

public class CpfAlreadyUsedException extends RuntimeException {
    public CpfAlreadyUsedException(){
        super("CPF already used by another employee");
    }

    public CpfAlreadyUsedException(String message) {
        super(message);
    }
}
