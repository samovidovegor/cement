
package org.example.exception;

public class InvalidOrderFileException extends RuntimeException {

    public InvalidOrderFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
