package by.kuzma.clever.hiber.exception;

import java.util.UUID;

public class NotFoundDataException extends RuntimeException{

    public NotFoundDataException() {
    }

    public NotFoundDataException(UUID id) {
        super("Product with id: " + id + " not found");
    }

    public NotFoundDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
