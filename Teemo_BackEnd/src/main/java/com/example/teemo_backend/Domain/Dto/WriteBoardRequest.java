package com.example.teemo_backend.Domain.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WriteBoardRequest {
    private String title;
    private String content;
    private long userId;


}
