package peaksoft.dto.requests;

import java.time.LocalDate;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 22.03.2023
 */
public record OneDayAvaragePriceRequest(
        Long id,
        LocalDate localDate
) {
}
