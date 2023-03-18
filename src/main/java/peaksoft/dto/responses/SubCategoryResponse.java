package peaksoft.dto.responses;

import lombok.Builder;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Builder
public record SubCategoryResponse(
        Long id,
        String name
) {
}
