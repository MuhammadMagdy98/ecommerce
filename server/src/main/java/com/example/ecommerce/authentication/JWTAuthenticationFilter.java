package com.example.ecommerce.authentication;
import com.example.ecommerce.DTO.ErrorDTO;
import com.example.ecommerce.util.JWTUtil;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

import org.springframework.stereotype.Component;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
public class JWTAuthenticationFilter implements WebFilter  {

    private final CustomUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(CustomUserDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                String username = jwtUtil.extractUsername(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Handle specific exceptions if needed
                    // Propagate the error
                    return userDetailsService.findByUsername(username)
                            .filter(userDetails -> jwtUtil.validateToken(token, userDetails.getUsername()))
                            .flatMap(userDetails -> {
                                UsernamePasswordAuthenticationToken authenticationToken =
                                        new UsernamePasswordAuthenticationToken(
                                                userDetails,
                                                null,
                                                userDetails.getAuthorities());
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                return chain.filter(exchange);
                            }).onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token.")));
                }
            } catch (SignatureException e) {
                System.out.println("in the exception");
                // Transform exception into a Mono error for proper handling
//                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token."));
//                ErrorDTO errorResponse = new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), "Invalid or expired token. Please log in again.");
                return Mono.error(new SignatureException("Invalid or expired token. Please log in again."));
            }
        }
        return chain.filter(exchange);
    }

}