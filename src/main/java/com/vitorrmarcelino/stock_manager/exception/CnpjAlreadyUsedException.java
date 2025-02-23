package com.vitorrmarcelino.stock_manager.exception;

public class CnpjAlreadyUsedException extends RuntimeException {
    public CnpjAlreadyUsedException(){
        super("CNPJ already used by another company");
    }

    public CnpjAlreadyUsedException(String message) {
        super(message);
    }
}
