package peaksoft.dto.requests;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import peaksoft.enums.Role;

import java.time.LocalDate;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
public record StatementRequest(
        String firstName,
        String lastName,
        LocalDate dateOfBrith,
        @Email
        String email,
        String password,
        String phoneNumber,
        @Enumerated(EnumType.STRING)
        Role role,
        Integer experience
) {
}
