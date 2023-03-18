package peaksoft.dto.requests;

import jakarta.validation.constraints.Email;
import lombok.Builder;

import java.time.LocalDate;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
@Builder
public record WaiterRequest(
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
