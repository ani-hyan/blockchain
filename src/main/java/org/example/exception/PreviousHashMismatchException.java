package org.example.exception;

public class PreviousHashMismatchException extends RuntimeException {
    public PreviousHashMismatchException(String message) {
        super("Previous hash mismatch: " + message);
    }
}
