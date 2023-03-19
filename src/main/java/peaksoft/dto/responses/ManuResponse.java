package peaksoft.dto.responses;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Builder
public record ManuResponse(
        Long id,
        String name,
        String image,
        BigDecimal price,
        String description,
        Boolean isVegetarian
) {
}
