package com.example.teemo_backend.Domain.Dto;


import com.example.teemo_backend.Domain.Entity.Category;
import com.example.teemo_backend.Domain.Entity.ConsumptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionRequest {

    private String consumptionType;
    private String category;
    private String paymentMethod;
    private String client;
    private int amount;
    private  String memo;
    private long userId;


}
