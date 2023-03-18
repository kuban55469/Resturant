package peaksoft.services;

import peaksoft.dto.requests.RestaurantRequest;
import peaksoft.dto.responses.RestaurantResponse;
import peaksoft.dto.responses.SimpleResponse;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
public interface RestaurantService {
    SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest);

    List<RestaurantResponse> findAll();

    SimpleResponse deleteRestaurant(Long restId);

    SimpleResponse updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest);

    RestaurantResponse findById(Long restId);
}
