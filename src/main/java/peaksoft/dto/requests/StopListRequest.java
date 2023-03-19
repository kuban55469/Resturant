package peaksoft.dto.requests;

import lombok.Builder;

import java.time.LocalDate;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.03.2023
 */
@Builder
public record StopListRequest(
        String reason,
        LocalDate date
) {
}
