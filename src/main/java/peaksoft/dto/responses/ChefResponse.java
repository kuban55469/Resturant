package peaksoft.dto.responses;




/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
public record ChefResponse(
        Long id,
        String firstName,
        String lastName,
        long dateOfBrith,
        String email,
        String phoneNumber,
        Integer experience
) {
}
