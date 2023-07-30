package com.example.teemo_backend.Service;

import com.example.teemo_backend.Domain.Dto.ConsumptionRequest;
import com.example.teemo_backend.Domain.Entity.Category;
import com.example.teemo_backend.Domain.Entity.ConsumptionType;
import com.example.teemo_backend.Domain.Entity.Production;
import com.example.teemo_backend.Exception.AppException;
import com.example.teemo_backend.Exception.ErrorCode;
import com.example.teemo_backend.Repository.ProductionRepository;
import com.example.teemo_backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductionService {


    private final ProductionRepository productionRepository;
    private final UserRepository userRepository;


    public String saveProduction(ConsumptionRequest consumption){

        String category =  Category.of(consumption.getCategory());

        userRepository.findById(consumption.getUserId())
                .orElseThrow(()->{throw new IllegalArgumentException("존재자히 않는 유저");});



        String consumptionType = ConsumptionType.SPEND.getConType();

        Production production =  Production.builder()
                .date(new Date())
                .amount(consumption.getAmount())
                .category(category)
                .amount(consumption.getAmount())
                .client(consumption.getClient())
                .memo(consumption.getMemo())
                .userId(consumption.getUserId())
                .paymentMethod(consumption.getPaymentMethod())
                .consumptionType(consumptionType)
                .build();


        productionRepository.save(production);


        return "저장 성공";
    }

    public List<Production> getList(long id){


        List<Production> list = productionRepository.findAllByUserId(id);

        return list;
    }


}
