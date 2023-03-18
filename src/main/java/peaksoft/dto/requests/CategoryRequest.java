package peaksoft.dto.requests;

import lombok.Builder;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Builder
public record CategoryRequest(
        String name
) {
}
