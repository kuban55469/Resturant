package peaksoft.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.MenuRequest;
import peaksoft.dto.responses.ManuResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.SubCategory;
import peaksoft.exeption.BadRequestException;
import peaksoft.exeption.NotFoundException;
import peaksoft.repositories.MenuItemRepository;
import peaksoft.repositories.RestaurantRepository;
import peaksoft.repositories.SubCategoryRepository;
import peaksoft.services.MenuItemService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final SubCategoryRepository subCategoryRepository;

    public MenuItemServiceImpl(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository,
                               SubCategoryRepository subCategoryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SimpleResponse saveManu(Long restId,Long subCategoryId, MenuRequest request) {
        if (!restaurantRepository.existsById(restId)){
            throw new NotFoundException(String.format("Restaurant with id: %d doesnt exist", restId));
        }

        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow();


        MenuItem menuItem = new MenuItem();
        menuItem.setName(request.name());
        menuItem.setImage(request.image());
        menuItem.setDescription(request.description());

        if (request.price().intValue() < 0){
            throw new BadRequestException("Price must be greater than 0");
        }

        menuItem.setPrice(request.price());
        menuItem.setIsVegetarian(request.isVegetarian());

        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).orElseThrow();
        menuItem.setSubCategory(subCategory);

        subCategory.addMenuItem(menuItem);
        restaurant.addMenu(menuItem);
        menuItem.setRestaurant(restaurant);
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).message(
                String.format("Manu with name: %s successfully SAVED", request.name())).build();
    }

    @Override
    public List<ManuResponse> findAllMenus(Long restId, String sort, Boolean isVegetarian) {
        if (sort.equals("ASC")) {
            return menuItemRepository.findAllMenusAsc(restId, isVegetarian);
        } else if (sort.equals("DESC")) {
            return menuItemRepository.findAllMenusDesc(restId, isVegetarian);
        }else {
            throw new BadRequestException("Tuura jaz!!!");
        }
    }

    @Override
    public ManuResponse findByMenuId(Long menuId) {
        return menuItemRepository.findByMenuId(menuId).orElseThrow(() -> new NotFoundException(
                String.format("Menu with id: %d doesn't exist", menuId)
        ));
    }

    @Override
    public SimpleResponse updateMenu(Long menuId, MenuRequest request) {
        if (!menuItemRepository.existsById(menuId)){
            throw new NotFoundException("Menu with id:" + menuId + " doesn't exist");
        }
        MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow();
        menuItem.setName(request.name());
        menuItem.setImage(request.image());
        if (request.price().intValue() < 0){
            throw new BadRequestException("Price must be greater than 0");
        }
        menuItem.setPrice(request.price());
        menuItem.setDescription(request.description());
        menuItem.setIsVegetarian(request.isVegetarian());

        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).message(
                String.format("Menu with name: %s successfully UPDATED", request.name())).build();
    }

    @Override
    public SimpleResponse deleteManu(Long restId, Long menuId) {
        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow(() -> new NotFoundException(
                String.format("Restaurant with id: %d doesn't exist", menuId)));
        MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow(() -> new NotFoundException(
                String.format("Menu with id: %d doesn't exist", menuId)
        ));


        restaurant.getMenuItems().removeIf(m -> m.getId().equals(menuId));

        menuItemRepository.delete(menuItem);
        menuItemRepository.deleteById(menuId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Menu with id: %d id successfully DELETED", menuId)).build();
    }
}
