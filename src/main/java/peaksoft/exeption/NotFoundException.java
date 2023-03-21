package peaksoft.exeption;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 21.03.2023
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
