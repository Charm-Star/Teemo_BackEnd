package com.example.teemo_backend.Domain.Dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserJoinRequest {
    @NonNull
    private String userEmail;
    @NonNull
    private String password;
    @NonNull
    private String nickname;


}
