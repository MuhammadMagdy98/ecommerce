package com.example.ecommerce.config;

//import com.example.ecommerce.authentication.CustomUserDetailsService;
//import com.example.ecommerce.authentication.JWTAuthenticationFilter;
import com.example.ecommerce.authentication.CustomAuthenticationEntryPoint;
import com.example.ecommerce.authentication.CustomAuthenticationFailureHandler;
import com.example.ecommerce.authentication.JWTAuthenticationFilter;
import com.example.ecommerce.constant.Url;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class Security {
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;
//    @Autowired
//    private ServerAuthenticationFailureHandler authenticationFailureHandler;
//

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Disable CSRF for stateless JWT-based authentication
                .authorizeExchange(exchanges -> exchanges
                                .pathMatchers(Url.LOGIN, Url.REGISTER, Url.PRODUCT).permitAll() // Allow public access to these routes
                        .anyExchange().authenticated() // All other routes require authentication
                )
                .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .httpBasic(httpBasicSpec -> httpBasicSpec.disable())
                .formLogin(formLoginSpec -> formLoginSpec.disable())

//                .logout(ServerHttpSecurity.LogoutSpec::disable) // Disable logout handling
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance()) // Stateless session management
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }







}
