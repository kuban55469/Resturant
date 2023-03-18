package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.requests.UserInfoRequest;
import peaksoft.dto.responses.UserInfoResponse;
import peaksoft.services.UserService;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
@RestController
@RequestMapping("/api/users")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public UserInfoResponse login(@RequestBody @Valid UserInfoRequest userInfoRequest){
        return userService.authenticate(userInfoRequest);
    }
}
