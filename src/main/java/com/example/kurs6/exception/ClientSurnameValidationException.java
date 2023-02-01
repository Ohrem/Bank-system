package com.example.kurs6.exception;

public class ClientSurnameValidationException extends RuntimeException{

    public ClientSurnameValidationException() {
        super();
    }

    public ClientSurnameValidationException(String message) {
        super(message);
    }

    public ClientSurnameValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientSurnameValidationException(Throwable cause) {
        super(cause);
    }
}
