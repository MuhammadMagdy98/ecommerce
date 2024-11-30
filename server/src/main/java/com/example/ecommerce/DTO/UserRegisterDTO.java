package com.example.ecommerce.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRegisterDTO {

    private String email;
    private String password;
    private String name;
}
