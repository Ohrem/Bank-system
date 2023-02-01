package com.example.kurs6.exception;

public class ClientNameValidationException extends RuntimeException{

    public ClientNameValidationException() {
    }

    public ClientNameValidationException(String message) {
        super(message);
    }

    public ClientNameValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNameValidationException(Throwable cause) {
        super(cause);
    }
}
