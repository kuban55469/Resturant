package peaksoft.dto.responses;

import jakarta.validation.constraints.Email;
import lombok.Builder;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
@Builder
public record UserInfoResponse(

        @Email
        String email,
        String token
) {
}
