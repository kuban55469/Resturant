package peaksoft.dto.responses;

import lombok.Builder;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Builder
public record CategoryResponse(
        Long id,
        String name
) {

}
