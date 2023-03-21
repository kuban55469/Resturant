package peaksoft.exeption;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 21.03.2023
 */
public class BadCredentialException extends RuntimeException{
    public BadCredentialException() {
        super();
    }

    public BadCredentialException(String message) {
        super(message);
    }
}
