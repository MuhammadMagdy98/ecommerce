package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication()
public class EcommerceApplication {

	public static void main(String[] args) {

		SpringApplication.run(EcommerceApplication.class, args);
	}

}
