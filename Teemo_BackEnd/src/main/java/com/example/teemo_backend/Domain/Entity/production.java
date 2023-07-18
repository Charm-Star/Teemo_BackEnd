package com.example.teemo_backend.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productionId;
    private ConsumptionType consumptionType;
    private Date date;
    private Category category;
    private String paymentMethod;
    private String client;
    private int amount;
    private  String memo;
    @JoinColumn(name = "id",nullable = false)
    private long userId;









}
