package peaksoft.dto.responses;


import java.time.LocalDate;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
public record ChefResponse(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBrith,
        String email,
        String phoneNumber,
        Integer experience
) {
}
