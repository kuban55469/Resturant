package peaksoft.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.ChefRequest;
import peaksoft.dto.responses.ChefResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repositories.RestaurantRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.services.ChefService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
@Service
public class ChefServiceImpl implements ChefService {
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ChefServiceImpl(RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public SimpleResponse saveCook(Long restId, ChefRequest cook) {
        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow(() -> new NoSuchElementException(
                String.format("Restaurant with id: %d not found.",restId )
        ));

        User user = new User();
        user.setFirstName(cook.firstName());
        user.setLastName(cook.lastName());
        user.setPhoneNumber(cook.phoneNumber());
        user.setEmail(cook.email());

        user.setRole(Role.CHEF);
        user.setPassword(passwordEncoder.encode(cook.password()));


        int count = restaurant.getUsers().size();
        if (count > 14){
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Sorry we haven't run out of vacancies").build();
        }else {
            restaurant.setNumberOfEmployees(++count);
            restaurantRepository.save(restaurant);
        }



        LocalDate now = LocalDate.now();
        int age = Period.between(cook.dateOfBrith(), now).getYears();
        if (age < 25 || age > 45){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("The age of the chef must be between 25 and 45 years old")
                    .build();
        }
        user.setDateOfBrith(cook.dateOfBrith());

        if (cook.experience() < 2){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("The experience of the chef must be 2 years old")
                    .build();
        }

        user.setExperience(cook.experience());
        restaurant.addChef(user);
        user.setRestaurant(restaurant);

        userRepository.save(user);

        return SimpleResponse.builder()
                .status(OK)
                .message(String.format("ChefApi with full name: %s is successfully saved.", cook.firstName()
                        .concat(" " + cook.lastName()))).build();
    }

    @Override
    public List<ChefResponse> findAllChefs(Long restId, Role role) {
        return userRepository.findAllChefs(restId, role);
    }

    @Override
    public ChefResponse findById(Long chefId, Role role) {
        return userRepository.findByChefId(chefId, role).orElseThrow(() -> new NoSuchElementException(String.format(
                "Chef with id: %d doesn't exist", chefId
        )));
    }


    @Override
    public SimpleResponse updateChefById(Long chefId, ChefRequest cook) {
        User user = userRepository.findById(chefId).orElseThrow(() -> new NoSuchElementException(
                String.format("Chef with id: %d doesn't exist", chefId)));

        user.setFirstName(cook.firstName());
        user.setLastName(cook.lastName());
        user.setPhoneNumber(cook.phoneNumber());
        user.setEmail(cook.email());

        user.setRole(Role.CHEF);
        user.setPassword(passwordEncoder.encode(cook.password()));

        LocalDate now = LocalDate.now();
        int age = Period.between(cook.dateOfBrith(), now).getYears();
        if (age < 25 || age > 45){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("The age of the chef must be between 25 and 45 years old")
                    .build();
        }
        user.setDateOfBrith(cook.dateOfBrith());

        if (cook.experience() <= 2){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("The experience of the chef must be 2 years old")
                    .build();
        }
        user.setExperience(cook.experience());

        userRepository.save(user);
        return SimpleResponse.builder().status(OK)
                .message("Chef with name " + cook.firstName() + " " + cook.lastName() + " is successfully updated").build();
    }

    @Override
    public SimpleResponse deleteChef(Long restId, Long chefId) {
        if (!userRepository.existsById(chefId)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Chef with id: %d is not found", chefId)).build();
        }

        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow(() -> new NoSuchElementException(
                String.format("Restaurant with id: %d doesn't exist", restId)));

        restaurant.getUsers().removeIf(chef ->chef.getId().equals(chefId));

        int count = restaurant.getUsers().size();
        if (count == 0){
            return SimpleResponse.builder()
                    .status(BAD_REQUEST)
                    .message("We have no employees").build();
        }
        restaurant.setNumberOfEmployees(count--);
        restaurantRepository.save(restaurant);

        userRepository.deleteById(chefId);

        return SimpleResponse.builder()
                .status(OK)
                .message(String.format(
                        "Chef with id: %d successfully deleted",chefId
                )).build();
    }

}
