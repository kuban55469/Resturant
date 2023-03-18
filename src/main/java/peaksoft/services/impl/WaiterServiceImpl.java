package peaksoft.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.WaiterRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.WaiterResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repositories.RestaurantRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.services.WaiterService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
@Service
public class WaiterServiceImpl implements WaiterService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public WaiterServiceImpl(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public SimpleResponse saveWaiter(Long restId, WaiterRequest waiter) {
        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow(() -> new NoSuchElementException(
                String.format("Restaurant with id: %d doesn't exist", restId)));

        User user = new User();
        user.setFirstName(waiter.firstName());
        user.setLastName(waiter.lastName());
        user.setEmail(waiter.email());
        user.setPassword(waiter.password());
        user.setPhoneNumber(waiter.phoneNumber());
        user.setRole(Role.WAITER);
        LocalDate now = LocalDate.now();
        int age = Period.between(waiter.dateOfBrith(), now).getYears();
        if (age < 18 || age > 30){
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("The age of the waiter must be between 18 and 30 years old")
                    .build();
        }
        user.setDateOfBrith(waiter.dateOfBrith());

        if (waiter.experience() < 1){
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("The experience of the waiter must be 1 years old")
                    .build();
        }
        user.setExperience(waiter.experience());

        restaurant.addWaiter(user);
        user.setRestaurant(restaurant);
        userRepository.save(user);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Waiter with full name: %s successfully saved",
                        waiter.firstName().concat(" " + waiter.lastName()))).build();
    }

    @Override
    public List<WaiterResponse> findAllWaiters(Long restId, Role role) {
        return userRepository.findAllWaiters(restId, role);
    }

    @Override
    public WaiterResponse findById(Long waiterId, Role role) {
        return userRepository.findByWaiterId(waiterId, role).orElseThrow(
                () -> new NoSuchElementException(String.format(
                        "Waiter with id: %d doesn't exist", waiterId
                ))
        );
    }

    @Override
    public SimpleResponse updateWaiter(Long waiterId, WaiterRequest waiter) {
        User user = userRepository.findById(waiterId).orElseThrow(() -> new NoSuchElementException(String.format(
                "Waiter with id: %d doesn't exist", waiterId)));
        user.setFirstName(waiter.firstName());
        user.setLastName(waiter.lastName());
        user.setEmail(waiter.email());
        user.setPassword(waiter.password());
        user.setPhoneNumber(waiter.phoneNumber());
        user.setRole(Role.WAITER);
        LocalDate now = LocalDate.now();
        int age = Period.between(waiter.dateOfBrith(), now).getYears();
        if (age < 18 || age > 30){
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("The age of the waiter must be between 18 and 30 years old")
                    .build();
        }
        user.setDateOfBrith(waiter.dateOfBrith());

        if (waiter.experience() < 1){
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("The experience of the waiter must be 1 years old")
                    .build();
        }
        user.setExperience(waiter.experience());

        userRepository.save(user);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Waiter with full name: %s successfully updated",
                        waiter.firstName().concat(" " + waiter.lastName()))).build();

    }

    @Override
    public SimpleResponse deleteWaiter(Long restId, Long waiterId) {

        if (!userRepository.existsById(waiterId)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Waiter with id: %d is not found", waiterId)).build();
        }

        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow(() -> new NoSuchElementException(
                String.format("Restaurant with id: %d doesn't exist", restId)));

        restaurant.getUsers().removeIf(w->w.getId().equals(waiterId));

        userRepository.deleteById(waiterId);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Waiter with id: %d is successfully deleted", waiterId))
                .build();
    }
}
