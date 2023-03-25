package peaksoft.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.ChequeRequest;
import peaksoft.dto.requests.OneDayAvaragePriceRequest;
import peaksoft.dto.responses.ChequeResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SimpleResponse1;
import peaksoft.services.ChequeService;

import java.util.List;


/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.03.2023
 */
@RestController
@RequestMapping("/api/cheques")
public class ChequeApi {

    private final ChequeService chequeService;

    public ChequeApi(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @PostMapping("/{restaurantId}/{waiterId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public SimpleResponse saveCheque(@PathVariable Long restaurantId,
                                     @PathVariable Long waiterId,
                                     @RequestBody ChequeRequest request){
        return chequeService.save(restaurantId,waiterId,request);
    }

    @GetMapping("/{waiterId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public List<ChequeResponse> findAll(@PathVariable Long waiterId){
        return chequeService.findAll(waiterId);
    }

    @PutMapping("/{chequeId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public SimpleResponse update(@PathVariable Long chequeId,
                                 @RequestBody ChequeRequest chequeRequest){
        return chequeService.update(chequeId, chequeRequest);
    }

    @DeleteMapping("/{waiterId}/{chequeId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long waiterId,
                                 @PathVariable Long chequeId){
        return chequeService.delete(waiterId,chequeId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public SimpleResponse1 oneDayAveragePrice(@RequestBody OneDayAvaragePriceRequest oneDayAvaragePriceRequest){
        return chequeService.oneDayAveragePrice(oneDayAvaragePriceRequest);
    }
}
