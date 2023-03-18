package peaksoft.dto.requests;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
public record ChefRequest(
        String firstName,
        String lastName,
        LocalDate dateOfBrith,
        @Email
        String email,
        String password,
        String phoneNumber,
        Integer experience
) {
}
