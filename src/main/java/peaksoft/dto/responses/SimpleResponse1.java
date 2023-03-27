package peaksoft.dto.responses;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 21.03.2023
 */
@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SimpleResponse1{
        String fullName;
        BigDecimal totalPrice;
}
