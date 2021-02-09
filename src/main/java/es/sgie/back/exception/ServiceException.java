package es.sgie.back.exception;

/**
 * Base exception to manage errors in service layer
 */
public class ServiceException extends Exception {

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }
}
