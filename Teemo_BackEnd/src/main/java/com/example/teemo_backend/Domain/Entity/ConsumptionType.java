package com.example.teemo_backend.Domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ConsumptionType {

    PROFIT("수익"),
    SPEND("소비");
    ;

    private String conType;
}
