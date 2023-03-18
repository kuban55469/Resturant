package peaksoft.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.RestaurantRequest;
import peaksoft.dto.responses.RestaurantResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.services.RestaurantService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantApi {
    private final RestaurantService restaurantService;


    public RestaurantApi(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.saveRestaurant(restaurantRequest);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RestaurantResponse> findAll (){
        return restaurantService.findAll();
    }

    @GetMapping("/{restId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RestaurantResponse getById(@PathVariable Long restId){
        return restaurantService.findById(restId);
    }
    @DeleteMapping("/{restId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteRestaurant(@PathVariable Long restId){
        return restaurantService.deleteRestaurant(restId);
    }

    @PutMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateRestaurant(@RequestBody RestaurantRequest restaurantRequest,
                                           @PathVariable Long restaurantId){
        return restaurantService.updateRestaurant(restaurantId,restaurantRequest);
    }
}
