package com.example.ecommerce.util;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;


public class CookieUtil {

    public static void addCookieToResponse(ServerHttpResponse response, String name, String value, int days) {
        ResponseCookie cookie = ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(false)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofDays(days))
                .build();

        response.addCookie(cookie);
    }

    public static void removeCookie(ServerHttpResponse response, String cookieName) {
        ResponseCookie expiredCookie = ResponseCookie.from(cookieName, "")
                .httpOnly(true)
                .secure(false)
                .sameSite("None")
                .path("/")
                .maxAge(0)
                .build();

        response.addCookie(expiredCookie);
    }

    public static Optional<String> extractCookieValue(ServerHttpRequest request, String cookieName) {
        return request.getCookies().getFirst(cookieName) != null
                ? Optional.of(Objects.requireNonNull(request.getCookies().getFirst(cookieName)).getValue())
                : Optional.empty();
    }
}

