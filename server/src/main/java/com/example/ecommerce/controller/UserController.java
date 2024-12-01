package com.example.ecommerce.controller;


import com.example.ecommerce.DTO.LoginResponseDTO;
import com.example.ecommerce.DTO.RegisterResponseDTO;
import com.example.ecommerce.DTO.ResponseDTO;
import com.example.ecommerce.DTO.UserLoginDTO;
import com.example.ecommerce.DTO.UserRegisterDTO;
import com.example.ecommerce.constant.Url;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping(Url.USER)
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Mono<ResponseDTO<RegisterResponseDTO>> create(
            @RequestBody UserRegisterDTO user,
            ServerHttpResponse response) {

        System.out.println("Hello in the register user");

        return userService.registerUser(user)
                .map(data -> {
                    // Create a cookie for the token
                    ResponseCookie tokenCookie = ResponseCookie.from("token", data.getToken())
                            .httpOnly(true)  // Set HttpOnly flag for security
                            .secure(false)
                            .sameSite("None")// Set Secure flag (use only with HTTPS)
                            .path("/")       // Set the path where the cookie is valid
                            .maxAge(Duration.ofDays(7))  // Set the cookie expiry time
                            .build();

                    // Add the cookie to the response
                    response.addCookie(tokenCookie);

                    // Return the response DTO
                    return new ResponseDTO<>(data, HttpStatus.CREATED.value(), null);
                });
    }

    @PostMapping("/login")
    public Mono<ResponseDTO<LoginResponseDTO>> login(@Valid @RequestBody UserLoginDTO user) {
        System.out.println("hello");
        return userService.loginUser(user).map(data -> new ResponseDTO<>(data, HttpStatus.OK.value(), null));
    }
}
