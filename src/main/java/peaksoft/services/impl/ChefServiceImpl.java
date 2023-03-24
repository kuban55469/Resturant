package peaksoft.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.ChefRequest;
import peaksoft.dto.responses.ChefResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exeption.BadRequestException;
import peaksoft.exeption.NotFoundException;
import peaksoft.repositories.RestaurantRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.services.ChefService;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow(() -> new NotFoundException(
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
            throw new BadRequestException("Sorry we haven't run out of vacancies");
        }else {
            restaurant.setNumberOfEmployees(++count);
            restaurantRepository.save(restaurant);
        }

        LocalDate now = LocalDate.now();
        int age = Period.between(cook.dateOfBrith(), now).getYears();
        if (age < 25 || age > 45){
            throw new BadRequestException("The age of the chef must be between 25 and 45 years old");
        }
        long u = ChronoUnit.YEARS.between(cook.dateOfBrith(), LocalDate.now());
        user.setDateOfBrith(u);
        if (cook.experience() < 2){
            throw new BadRequestException("The experience of the chef must be 2 years old");
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
        if (!restaurantRepository.existsById(restId)){
            throw new NotFoundException("Restaurant with id not found!");
        }
        return userRepository.findAllChefs(restId, role);
    }

    @Override
    public ChefResponse findById(Long chefId, Role role) {
        return userRepository.findByChefId(chefId, role).orElseThrow(() -> new NotFoundException(String.format(
                "Chef with id: %d doesn't exist", chefId
        )));
    }


    @Override
    public SimpleResponse updateChefById(Long chefId, ChefRequest cook) {
        User user = userRepository.findById(chefId).orElseThrow(() -> new NotFoundException(
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
            throw new BadRequestException("The age of the chef must be between 25 and 45 years old");
        }
        long u = ChronoUnit.YEARS.between(cook.dateOfBrith(), LocalDate.now());
        user.setDateOfBrith(u);
        if (cook.experience() <= 2){
            throw new BadRequestException("The experience of the chef must be 2 years old");
        }
        user.setExperience(cook.experience());

        userRepository.save(user);
        return SimpleResponse.builder().status(OK)
                .message("Chef with name " + cook.firstName() + " " + cook.lastName() + " is successfully updated").build();
    }

    @Override
    public SimpleResponse deleteChef(Long restId, Long chefId) {
        if (!userRepository.existsById(chefId)) {
            throw new NotFoundException(String.format("Chef with id: %d is not found", chefId));
        }

        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow(() -> new NotFoundException(
                String.format("Restaurant with id: %d doesn't exist", restId)));

        restaurant.getUsers().removeIf(chef ->chef.getId().equals(chefId));

        int count = restaurant.getUsers().size();
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
