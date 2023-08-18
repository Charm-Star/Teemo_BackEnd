package com.example.teemo_backend.Domain.Dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@AllArgsConstructor
public class ChangePwRequest {
    @NonNull
    private String exPassword;
    @NonNull
    private String newPassword;
    @NonNull
    private String newPasswordCheck;
}
