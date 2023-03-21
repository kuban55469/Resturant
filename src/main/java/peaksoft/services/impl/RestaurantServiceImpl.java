package peaksoft.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.RestaurantRequest;
import peaksoft.dto.responses.RestaurantResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.entity.Restaurant;
import peaksoft.exeption.NotFoundException;
import peaksoft.repositories.RestaurantRepository;
import peaksoft.services.RestaurantService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest request) {

        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.name());
        restaurant.setLocation(request.location());
        restaurant.setRestType(request.restType());
        restaurant.setService(request.service());

        restaurantRepository.save(restaurant);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Restaurant with name: %s is successfully saved.", request.name()))
                .build();
    }


    @Override
    public List<RestaurantResponse> findAll() {
        return restaurantRepository.findAllRestaurants();
    }



    @Override
    public SimpleResponse deleteRestaurant(Long restId) {

        if (!restaurantRepository.existsById(restId)) {
            throw new NotFoundException(String.format("Restaurant with id: %d doesn't exist", restId));
        }


        restaurantRepository.deleteById(restId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Restaurant with id: %d is successfully deleted.", restId))
                .build();
    }

    @Override
    public SimpleResponse updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Restaurant with id: %d in not", restaurantId)));
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());

        restaurantRepository.save(restaurant);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .message(String.format("Restaurant with name: %s successfully updated",restaurantRequest.name()))
                .build();
    }

    @Override
    public RestaurantResponse findById(Long restId) {
        if (!restaurantRepository.existsById(restId)){
            throw new NotFoundException(String.format("Restaurant with id: %d doesnt exist", restId));
        }
        return restaurantRepository.findRestaurantById(restId);
    }
}
