package com.example.ecommerce.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.SignatureException;


@Component
public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException exception) {
        // Determine the appropriate error message and status code
        HttpStatus status;
        String errorMessage;

        if (exception.getCause() instanceof SignatureException) {
            status = HttpStatus.UNAUTHORIZED;
            errorMessage = "Invalid or expired token. Please log in again.";
        } else {
            status = HttpStatus.FORBIDDEN; // For other types of authentication failures
            errorMessage = "Authentication failed. Please check your credentials.";
        }

        // Set the response status
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Create the error message
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        DataBuffer buffer = bufferFactory.wrap(errorMessage.getBytes(StandardCharsets.UTF_8));

        // Write the error message to the response body
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

}