package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.ChefRequest;
import peaksoft.dto.responses.ChefResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.enums.Role;
import peaksoft.services.ChefService;

import java.util.List;


/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
@RestController
@RequestMapping("/api/chefs")
public class ChefApi {
    private final ChefService chefService;

    public ChefApi(ChefService chefService) {
        this.chefService = chefService;
    }

    @PostMapping("/{restId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveCook(@RequestBody @Valid ChefRequest chefRequest,
                                   @PathVariable Long restId){
        return chefService.saveCook(restId, chefRequest);
    }

    @GetMapping("/{restId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ChefResponse> findAllChefs (@PathVariable Long restId,
                                            @RequestParam Role role){
        return chefService.findAllChefs(restId, role);
    }

    @GetMapping("/getById/{chefId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ChefResponse getById(@PathVariable Long chefId,
                                @RequestParam Role role){
        return chefService.findById(chefId, role);
    }

    @PutMapping("/{chefId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateChef(@PathVariable Long chefId,
                                     @RequestBody @Valid ChefRequest chefRequest){
        return chefService.updateChefById(chefId, chefRequest);
    }

    @DeleteMapping("/{restId}/{chefId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteChef(@PathVariable Long restId, @PathVariable Long chefId){
        return chefService.deleteChef(restId, chefId);
    }

}
