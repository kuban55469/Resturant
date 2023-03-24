package peaksoft.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.StopListRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.StopListResponse;
import peaksoft.services.StopListService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.03.2023
 */
@RestController
@RequestMapping("/api/stopList")
public class StopListApi {

    private final StopListService stopListService;

    public StopListApi(StopListService stopListService) {
        this.stopListService = stopListService;
    }

    @PostMapping("/{menuItemId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    public SimpleResponse saveStopList(@RequestBody StopListRequest request,
                                       @PathVariable Long menuItemId){
        return stopListService.saveStopList(request, menuItemId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF', 'WAITER')")
    public List<StopListResponse> findAll(){
        return stopListService.findAll();
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF', 'WAITER')")
    public StopListResponse getById(@PathVariable Long id){
        return stopListService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody StopListRequest request){
        return stopListService.update(id, request);
    }

    @DeleteMapping("/{menuId}/{stopListId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    public SimpleResponse delete(@PathVariable Long menuId, @PathVariable Long stopListId){
        return stopListService.delete(menuId, stopListId);
    }
}
