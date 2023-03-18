package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.WaiterRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.WaiterResponse;
import peaksoft.enums.Role;
import peaksoft.services.WaiterService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
@RestController
@RequestMapping("/api/waiters")
public class WaiterApi {

    private final WaiterService waiterService;

    @Autowired
    public WaiterApi(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @PostMapping("/{restId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveWaiter (@RequestBody WaiterRequest waiter,
                                      @PathVariable Long restId){
        return waiterService.saveWaiter(restId, waiter);
    }

    @GetMapping("/findAll/{restId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<WaiterResponse> findAllWaiters(@PathVariable Long restId,
                                               @RequestParam Role role){
        return waiterService.findAllWaiters(restId, role);
    }

    @GetMapping("/{waiterId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public WaiterResponse findById(@PathVariable Long waiterId,
                                    @RequestParam Role role){
        return waiterService.findById(waiterId, role);
    }

    @PutMapping("/{waiterId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateWaiter(@PathVariable Long waiterId,
                                       @RequestBody WaiterRequest waiterRequest){
        return waiterService.updateWaiter(waiterId, waiterRequest);
    }

    @DeleteMapping("/{restId}/{waiterId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteWaiter(@PathVariable Long restId,
                                       @PathVariable Long waiterId){
        return waiterService.deleteWaiter(restId, waiterId);
    }
}
