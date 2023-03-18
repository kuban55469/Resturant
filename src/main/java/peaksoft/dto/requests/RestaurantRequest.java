package peaksoft.dto.requests;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import peaksoft.enums.RestType;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
public record RestaurantRequest(
        String name,
        String location,
        @Enumerated(EnumType.STRING)
        RestType restType,
        Integer service
) {
}
