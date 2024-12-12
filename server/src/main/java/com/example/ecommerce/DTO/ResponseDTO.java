package com.example.ecommerce.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

    private T data;

    private int status;

    private String message;
    public static <T> ResponseDTO<T> of(T data, int statusCode, String errorMessage) {
        return new ResponseDTO<>(data, statusCode, errorMessage);
    }

}
