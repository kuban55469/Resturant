package peaksoft.services;

import peaksoft.dto.requests.ChequeRequest;
import peaksoft.dto.requests.OneDayAvaragePriceRequest;
import peaksoft.dto.responses.ChequeResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SimpleResponse1;

import java.time.LocalDate;
import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.03.2023
 */
public interface ChequeService {
    SimpleResponse save(Long restaurantId, Long waiterId, ChequeRequest request);

    List<ChequeResponse> findAll(Long waiterId);

    SimpleResponse update(Long waiterId, Long chequeId, ChequeRequest chequeRequest);


    SimpleResponse delete(Long waiterId, Long chequeId);

    SimpleResponse1 oneDayAveragePrice(OneDayAvaragePriceRequest oneDayAvaragePriceRequest);

}
