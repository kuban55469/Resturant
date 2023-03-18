package peaksoft.dto.responses;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import peaksoft.enums.RestType;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
@Builder
public record RestaurantResponse(
        Long id,
        String name,
        String location,
        @Enumerated(EnumType.STRING)
        RestType restType,
        Integer numberOfEmployees,
        Integer service
) {
}
