package com.example.ecommerce.DTO;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserDetailsDTO {
    String email;
    int userId;
    String username;
    @Nullable
    String address;
    @Nullable
    String firstName;
    @Nullable
    String lastName;
    @Nullable
    String role;
    @Nullable
    String phoneNumber;

}
