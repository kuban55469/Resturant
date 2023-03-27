package peaksoft.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.MenuRequest;
import peaksoft.dto.responses.ManuResponse;
import peaksoft.dto.responses.MenuItemResponseSearch;
import peaksoft.dto.responses.PaginationResponseMenu;
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

    @PostMapping("/{restId}/{subId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    public SimpleResponse saveManu(@PathVariable Long restId,
                                   @RequestBody MenuRequest menuRequest,
                                   @PathVariable Long subId){
        return menuItemService.saveManu(restId,subId, menuRequest);
    }

    @GetMapping("/findAll/{restId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF', 'WAITER')")
    public List<ManuResponse> findAllMenus(@PathVariable Long restId,
                                           @RequestParam String sort,
                                           @RequestParam Boolean isVegetarian){
        return menuItemService.findAllMenus(restId, sort, isVegetarian);
    }

    @GetMapping("/getById/{menuId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF', 'WAITER')")
    public ManuResponse findByManuId(@PathVariable Long menuId){
        return menuItemService.findByMenuId(menuId);
    }


    @PutMapping("/{menuId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    public SimpleResponse updateMenu(@PathVariable Long menuId,
                                     @RequestBody MenuRequest request){
        return menuItemService.updateMenu(menuId, request);
    }

    @DeleteMapping("/{restId}/{menuId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    public SimpleResponse deleteMenu(@PathVariable Long restId,
                                     @PathVariable Long menuId){
        return menuItemService.deleteManu(restId, menuId);
    }

    @GetMapping("/pagination/{subId}")
    public PaginationResponseMenu getMenuPage(@RequestParam int page,
                                              @RequestParam int size,
                                              @PathVariable Long subId){
        return menuItemService.getMenuPage(subId, page, size);
    }

    @GetMapping("/search")
    public List<MenuItemResponseSearch> search (@RequestParam String keyWord){
        return menuItemService.search(keyWord);
    }
}
