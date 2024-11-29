package com.example.ecommerce.service;

import com.example.ecommerce.DTO.LoginResponseDTO;
import com.example.ecommerce.DTO.RegisterResponseDTO;
import com.example.ecommerce.DTO.UserLoginDTO;
import com.example.ecommerce.DTO.UserRegisterDTO;
import reactor.core.publisher.Mono;

public interface UserService {


    Mono<RegisterResponseDTO> registerUser(UserRegisterDTO userDTO);
    Mono<LoginResponseDTO> loginUser(UserLoginDTO userDTO);

}
