package peaksoft.exeption;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 21.03.2023
 */
public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
