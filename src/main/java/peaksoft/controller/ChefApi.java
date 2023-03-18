package peaksoft.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.CookRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.services.ChefService;


/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
@RestController
@RequestMapping("/api/chefs")
public class Chef {
    private final ChefService chefService;

    public Chef(ChefService chefService) {
        this.chefService = chefService;
    }

    @PostMapping("/{restId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveCook(@RequestBody CookRequest cookRequest,
                                   @PathVariable Long restId){
        return chefService.saveCook(restId, cookRequest);
    }
}
