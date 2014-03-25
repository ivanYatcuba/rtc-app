package net.github.rtc.web.courses.exception;

/**
 * Service processing exception
 *
 * @author Vladislav Pikus
 */
public class ServiceProcessingException extends RuntimeException {
    public ServiceProcessingException() {
    }

    public ServiceProcessingException(String message) {
        super(message);
    }
}
