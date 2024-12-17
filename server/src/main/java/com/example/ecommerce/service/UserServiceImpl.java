package com.example.ecommerce.service;

import com.example.ecommerce.DTO.LoginResponseDTO;
import com.example.ecommerce.DTO.RegisterResponseDTO;
import com.example.ecommerce.DTO.UserDetailsDTO;
import com.example.ecommerce.DTO.UserLoginDTO;
import com.example.ecommerce.DTO.UserRegisterDTO;
import com.example.ecommerce.exception.EcommerceError;
import com.example.ecommerce.exception.EcommerceException;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.util.JWTUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Mono<RegisterResponseDTO> registerUser(UserRegisterDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail())
                .flatMap(existingUser -> {
                    // User already exists, return an error with a specific type
                    return Mono.error(new EcommerceException(EcommerceError.USER_ALREADY_EXIST));
                })
                .switchIfEmpty(Mono.defer(() -> {
                    // Create and save the new user
                    User newUser = new User();
                    newUser.setEmail(userDTO.getEmail());
                    newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    // Encode password
                    newUser.setUsername(userDTO.getName());

                    return userRepository.save(newUser)
                            .map(savedUser -> {
                                // Generate JWT token
                                String token = jwtUtil.generateToken(savedUser.getEmail());
                                // Create response DTO
                                RegisterResponseDTO response = new RegisterResponseDTO();
                                response.setToken(token);
                                return response; // Return the correct type (RegisterResponseDTO)
                            });
                }))
                // This cast ensures that the whole chain results in Mono<RegisterResponseDTO>
                .cast(RegisterResponseDTO.class);
    }

    @Override
    public Mono<LoginResponseDTO> loginUser(UserLoginDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail()).flatMap(existingUser -> {
            String hashedPassword = existingUser.getPassword();
            if (passwordEncoder.matches(userDTO.getPassword(), hashedPassword)) {
                String token = jwtUtil.generateToken(existingUser.getEmail());

                LoginResponseDTO response = new LoginResponseDTO();
                response.setToken(token);
                return Mono.just(response);
            } else {
                return Mono.error(new EcommerceException(EcommerceError.INVALID_CREDENTIALS));
            }
        });
    }

    @Override
    public Mono<UserDetailsDTO> getUserDetails(String token) {
        String username = jwtUtil.extractUsername(token);
        if (username == null) {
            return Mono.error(new EcommerceException(EcommerceError.UNAUTHORIZED));
        }
        return userRepository.findByEmail(username)
                .switchIfEmpty(Mono.error(new EcommerceException(EcommerceError.NOT_FOUND)))
                .map(user -> {
                    // Transform the user entity to UserDetailsDTO
                    return UserDetailsDTO.builder()
                            .userId(user.getUserId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .phoneNumber(user.getPhoneNumber())
                            .address(user.getAddress())
                            .role(user.getRole())
                            .build();
                });

    }

}
