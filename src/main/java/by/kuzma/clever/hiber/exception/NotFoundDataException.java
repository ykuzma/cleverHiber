package by.kuzma.clever.hiber.exception;

import java.util.UUID;

public class NotFoundDataException extends RuntimeException{

    public NotFoundDataException() {
    }

    public NotFoundDataException(UUID id, Class<?> clazz) {
        super(String.format("%s with id: %s not found", clazz.getSimpleName(), id));
    }

    public NotFoundDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
