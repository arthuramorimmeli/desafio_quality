package com.mercadolivre.wave4.desafio_quality.shared.exceptions;

public class CreatePropertyException extends RuntimeException {
    public CreatePropertyException(String message) {
        super(message);
    }
    public CreatePropertyException(Throwable cause) {
        super(cause);
    }
    public CreatePropertyException(RuntimeException e) {
        super(e);
    }
}
