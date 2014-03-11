package com.springapp.mvc.courses.exception;

/**
 * @author Vladislav Pikus
 */
public class NullIdException extends RuntimeException {
    public NullIdException() {
    }

    public NullIdException(String message) {
        super(message);
    }
}
