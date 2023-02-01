package com.example.kurs6.exception;

public class ClientDataValidationException extends RuntimeException{

    public ClientDataValidationException() {
        super();
    }

    public ClientDataValidationException(String message) {
        super(message);
    }

    public ClientDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientDataValidationException(Throwable cause) {
        super(cause);
    }
}
