package com.mercadolivre.wave4.desafio_quality.shared.exceptions;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String message) {
        super(message);
    }
    public RepositoryException(Throwable cause) {
        super(cause);
    }
    public RepositoryException(RuntimeException e) {
        super(e);
    }
}

