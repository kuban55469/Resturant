package peaksoft.services;

import peaksoft.dto.requests.MenuRequest;
import peaksoft.dto.responses.ManuResponse;
import peaksoft.dto.responses.SimpleResponse;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
public interface MenuItemService {
    SimpleResponse saveManu(Long restId, MenuRequest menuRequest);

    List<ManuResponse> findAllMenus(Long restId, String sort, Boolean isVegetarian);

    ManuResponse findByMenuId(Long menuId);

    SimpleResponse updateMenu(Long menuId, MenuRequest request);

    SimpleResponse deleteManu(Long restId, Long menuId);
}
