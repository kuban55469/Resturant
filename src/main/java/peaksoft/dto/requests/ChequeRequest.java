package peaksoft.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.03.2023
 */
@Builder
@Data

@AllArgsConstructor
@NoArgsConstructor
public class ChequeRequest {
        private List<Long> id;

}
