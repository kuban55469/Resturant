package peaksoft.dto.responses;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import peaksoft.enums.Role;

import java.time.LocalDate;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Builder
public record StatementResponse(
        Long id,
        String firstName,
        String lastName,
        long dateOfBrith,
        String email,
        String phoneNumber,
        @Enumerated(EnumType.STRING)
        Role role,
        Integer experience
) {
}
