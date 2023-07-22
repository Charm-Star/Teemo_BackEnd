package com.example.teemo_backend.Domain.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class RefreshToken {
    private String token;
    private long id;
}
