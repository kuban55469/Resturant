package peaksoft.services.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtUtil;
import peaksoft.dto.requests.UserInfoRequest;
import peaksoft.dto.responses.UserInfoResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repositories.UserRepository;
import peaksoft.services.UserService;

import java.util.NoSuchElementException;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 16.03.2023
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;

    }


    @Override
    public UserInfoResponse authenticate(UserInfoRequest userInfoRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userInfoRequest.email(),
                        userInfoRequest.password()
                )
        );

        User user = userRepository.findByEmail(userInfoRequest.email())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("User with email: %s doesn't exists", userInfoRequest.email())));
        String token = jwtUtil.generateToken(user);

        return UserInfoResponse.builder().token(token).email(userInfoRequest.email()).build();
    }


    @PostConstruct
    public void init(){
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRole(Role.ADMIN);
        if (!userRepository.existsByEmail(user.getEmail())){
            userRepository.save(user);
        }
    }
}
