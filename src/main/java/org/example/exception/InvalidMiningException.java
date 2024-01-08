package org.example.exception;

public class InvalidMiningException extends RuntimeException {
    public InvalidMiningException(String message) {
        super("Invalid block: " + message);
    }
}
