package com.example.teemo_backend.Domain.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequest {



    private String email;
    private String password;
    public UserLoginRequest() {


    }
}
