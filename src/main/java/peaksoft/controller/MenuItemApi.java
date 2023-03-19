package peaksoft.controller;

import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.MenuRequest;
import peaksoft.dto.responses.ManuResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.services.MenuItemService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@RestController
@RequestMapping("/api/menu")
public class MenuItemApi {

    private final MenuItemService menuItemService;

    public MenuItemApi(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("/{restId}")
    public SimpleResponse saveManu(@PathVariable Long restId,
                                   @RequestBody MenuRequest menuRequest){
        return menuItemService.saveManu(restId, menuRequest);
    }

    @GetMapping("/findAll/{restId}")
    public List<ManuResponse> findAllMenus(@PathVariable Long restId,
                                           @RequestParam String sort,
                                           @RequestParam Boolean isVegetarian){
        return menuItemService.findAllMenus(restId, sort, isVegetarian);
    }

    @GetMapping("/getById/{menuId}")
    public ManuResponse findByManuId(@PathVariable Long menuId){
        return menuItemService.findByMenuId(menuId);
    }


    @PutMapping("/{menuId}")
    public SimpleResponse updateMenu(@PathVariable Long menuId,
                                     @RequestBody MenuRequest request){
        return menuItemService.updateMenu(menuId, request);
    }

    @DeleteMapping("/{restId}/{menuId}")
    public SimpleResponse deleteMenu(@PathVariable Long restId,
                                     @PathVariable Long menuId){
        return menuItemService.deleteManu(restId, menuId);
    }
}
