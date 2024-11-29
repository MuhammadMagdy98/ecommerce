package com.example.ecommerce.repository;

import com.example.ecommerce.model.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User, Integer> {

    Mono<User> findByEmail(String email);
}
