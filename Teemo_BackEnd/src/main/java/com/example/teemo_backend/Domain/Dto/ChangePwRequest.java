package com.example.teemo_backend.Domain.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ChangePwRequest {

    private String ExPassword;
    private String newPassword;
    private String newPasswordCheck;
}