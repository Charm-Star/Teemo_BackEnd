package com.example.teemo_backend.Domain.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserJoinRequest {
    private String userEmail;
    private String password;
    private String nickname;


}
