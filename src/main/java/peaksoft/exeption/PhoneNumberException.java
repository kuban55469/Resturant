package peaksoft.exeption;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 25.03.2023
 */
public class PhoneNumberException extends RuntimeException{
    public PhoneNumberException() {
        super();
    }

    public PhoneNumberException(String message) {
        super(message);
    }
}
