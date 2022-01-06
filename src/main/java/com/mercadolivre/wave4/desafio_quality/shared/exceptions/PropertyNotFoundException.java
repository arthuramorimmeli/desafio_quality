package com.mercadolivre.wave4.desafio_quality.shared.exceptions;

public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException(String message) {
        super(message);
    }
    public PropertyNotFoundException(Throwable cause) {
        super(cause);
    }
    public PropertyNotFoundException(RuntimeException e) {
        super(e);
    }
}
