package peaksoft.dto.requests;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Builder
public record MenuRequest(
        String name,
        String image,
        BigDecimal price,
        String description,
        Boolean isVegetarian
) {
}
