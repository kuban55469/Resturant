package peaksoft.dto.responses;

import lombok.Builder;


import java.time.LocalDate;
import java.time.Year;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
@Builder
public record WaiterResponse(
        Long id,
        String firstName,
        String lastName,
        long dateOfBrith,
        String email,
        String phoneNumber,
        Integer experience
) {
}
