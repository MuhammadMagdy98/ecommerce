package com.example.ecommerce.authentication;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class CustomAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {


    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        // Customize the response here
        ServerWebExchange exchange = webFilterExchange.getExchange();

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // Write a custom error message to the response
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        DataBuffer buffer = bufferFactory.wrap("Invalid or expired token".getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}
