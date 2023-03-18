package peaksoft.dto.responses;

import lombok.Builder;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
@Builder
public record UserInfoResponse(

        String email,
        String token
) {
}
