package peaksoft.repositories;

import org.springframework.http.HttpHeaders;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
public record SimpleResponse(
        HttpHeaders httpHeaders,
        String message
) {
}
