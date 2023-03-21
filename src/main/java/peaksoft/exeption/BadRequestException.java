package peaksoft.exeption;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 21.03.2023
 */
public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
