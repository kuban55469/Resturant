package peaksoft.dto.responses;

import lombok.Builder;

import java.time.LocalDate;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.03.2023
 */
@Builder
public record StopListResponse(
        Long id,
        String menuItemName,
        String reason,
        LocalDate date
) {
}
