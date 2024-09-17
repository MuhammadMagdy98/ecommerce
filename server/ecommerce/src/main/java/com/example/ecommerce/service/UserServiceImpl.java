package com.example.ecommerce.service;

import com.example.ecommerce.DTO.LoginResponseDTO;
import com.example.ecommerce.DTO.RegisterResponseDTO;
import com.example.ecommerce.DTO.UserLoginDTO;
import com.example.ecommerce.DTO.UserRegisterDTO;
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
                    return Mono.error(new RuntimeException("Email already in use"));
                })
                .switchIfEmpty(Mono.defer(() -> {
                    // Create and save the new user
                    User newUser = new User();
                    newUser.setEmail(userDTO.getEmail());
                    newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    // Encode password
                    System.out.println("setting the username");
                    newUser.setUsername(userDTO.getUsername());

                    return userRepository.save(newUser)
                            .map(savedUser -> {
                                // Generate JWT token
                                String token = jwtUtil.generateToken(savedUser.getEmail());
                                System.out.println("token is " + token);
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
                 return Mono.error(new RuntimeException("Email or password is not correct"));
             }
        });
    }

}