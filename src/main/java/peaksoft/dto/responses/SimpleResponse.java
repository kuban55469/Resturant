package peaksoft.dto.responses;

import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
@Builder
public record SimpleResponse(
        HttpStatus status,
        String message
) {
}
