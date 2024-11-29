package com.example.ecommerce.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginDTO {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
