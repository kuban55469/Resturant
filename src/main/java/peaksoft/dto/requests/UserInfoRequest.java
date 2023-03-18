package peaksoft.dto.requests;

import jakarta.validation.constraints.Email;
import lombok.Builder;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
@Builder
public record UserInfoRequest(
        @Email
        String email,
        String password
) {
}
