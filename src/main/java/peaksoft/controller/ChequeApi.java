package peaksoft.controller;

import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.ChequeRequest;
import peaksoft.dto.responses.AvaregeResponse;
import peaksoft.dto.responses.ChequeResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SimpleResponse1;
import peaksoft.services.ChequeService;

import java.math.BigDecimal;
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
    public SimpleResponse saveCheque(@PathVariable Long restaurantId,
                                     @PathVariable Long waiterId,
                                     @RequestBody ChequeRequest request){
        return chequeService.save(restaurantId,waiterId,request);
    }

    @GetMapping("/{waiterId}")
    public List<ChequeResponse> findAll(@PathVariable Long waiterId){
        return chequeService.findAll(waiterId);
    }

    @PutMapping("/{waiterId}/{chequeId}")
    public SimpleResponse update(@PathVariable Long waiterId,
                                 @PathVariable Long chequeId,
                                 @RequestBody ChequeRequest chequeRequest){
        return chequeService.update(waiterId, chequeId, chequeRequest);
    }

    @DeleteMapping("/{waiterId}/{chequeId}")
    public SimpleResponse delete(@PathVariable Long waiterId,
                                 @PathVariable Long chequeId){
        return chequeService.delete(waiterId,chequeId);
    }

//    @GetMapping("/{waiterId}")
//    public SimpleResponse1 oneDayAveragePrice(@PathVariable Long waiterId){
//        return chequeService.oneDayAveragePrice(waiterId);
//    }
}
