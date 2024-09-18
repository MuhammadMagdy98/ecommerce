package com.example.ecommerce.exception;

import com.example.ecommerce.DTO.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import io.jsonwebtoken.security.SignatureException;

import java.nio.charset.StandardCharsets;

@ControllerAdvice
@Order(-2)
public class GlobalException implements WebExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper(); // To serialize ErrorDTO into JSON

    // Custom method to build the error response using ErrorDTO
    private Mono<Void> buildErrorResponse(ServerWebExchange exchange, HttpStatus status, String message) {
        ErrorDTO errorResponse = new ErrorDTO(status.value(), message);
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        try {
            // Convert ErrorDTO to JSON string
            byte[] bytes = objectMapper.writeValueAsString(errorResponse).getBytes(StandardCharsets.UTF_8);
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
        } catch (Exception e) {
            // In case serialization fails, fallback to a simple message
            byte[] fallbackBytes = "{\"status\":500,\"message\":\"Error serializing the error response.\"}".getBytes(StandardCharsets.UTF_8);
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(fallbackBytes)));
        }
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof SignatureException) {
            return buildErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid or expired token. Please log in again.");
        }
        if (ex instanceof EcommerceException) {
            return buildErrorResponse(exchange, HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        return buildErrorResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");

    }
}
