package peaksoft.dto.responses;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.03.2023
 */
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChequeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private List<ManuResponse> manuResponses;
    private BigDecimal averagePrice;
    private Integer service;
    private BigDecimal grandTotal;

    public ChequeResponse(Long id,String firstName,String lastName, Integer service) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.service = service;
    }
}
