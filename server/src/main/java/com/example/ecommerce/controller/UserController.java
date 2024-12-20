package com.example.ecommerce.controller;


import com.example.ecommerce.DTO.LoginResponseDTO;
import com.example.ecommerce.DTO.RegisterResponseDTO;
import com.example.ecommerce.DTO.ResponseDTO;
import com.example.ecommerce.DTO.UserDetailsDTO;
import com.example.ecommerce.DTO.UserLoginDTO;
import com.example.ecommerce.DTO.UserRegisterDTO;
import com.example.ecommerce.constant.Url;
import com.example.ecommerce.exception.EcommerceError;
import com.example.ecommerce.exception.EcommerceException;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.example.ecommerce.util.CookieUtil;


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

        log.info("Registering user: {}", user.getEmail());

        return userService.registerUser(user)
                .map(data -> {
                    CookieUtil.addCookieToResponse(response, "token", data.getToken(), 7);

                    // Return the response DTO
                    return ResponseDTO.of(data, HttpStatus.CREATED.value(), null);
                });
    }

    @PostMapping("/login")
    public Mono<ResponseDTO<LoginResponseDTO>> login(@Valid @RequestBody UserLoginDTO user, ServerHttpResponse response) {
        log.info("Logging in user: {}", user.getEmail());
        return userService.loginUser(user).map(data -> {
            CookieUtil.addCookieToResponse(response, "token", data.getToken(), 7);
            return ResponseDTO.of(data, HttpStatus.OK.value(), null);
        });
    }

    @PostMapping("/logout")
    public Mono<Void> logout(ServerHttpResponse response) {
        CookieUtil.removeCookie(response, "token");
        return Mono.empty();
    }

    @GetMapping("/me")
    public Mono<ResponseDTO<UserDetailsDTO>> getAuthenticatedUser(ServerHttpResponse response,
                                                                  @CookieValue(name = "token", required = false) String token) {

        if (token == null || token.isEmpty()) {
            log.warn("No token provided in cookies");
            return Mono.error(new EcommerceException(EcommerceError.UNAUTHORIZED));
        }

        return userService.getUserDetails(token)
                .map(userDetails -> ResponseDTO.of(userDetails, HttpStatus.OK.value(), null));

    }


}
