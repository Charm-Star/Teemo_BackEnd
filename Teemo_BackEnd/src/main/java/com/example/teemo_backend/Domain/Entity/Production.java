package com.example.teemo_backend.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productionId;
    private String consumptionType;
    private Date date;
    private String category;
    private String paymentMethod;
    private String client;
    private int amount;
    private  String memo;
    private long userId;


}
