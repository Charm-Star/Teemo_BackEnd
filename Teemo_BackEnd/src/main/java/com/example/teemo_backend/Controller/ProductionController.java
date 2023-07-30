package com.example.teemo_backend.Controller;


import com.example.teemo_backend.Domain.Dto.ConsumptionRequest;
import com.example.teemo_backend.Domain.Entity.Production;
import com.example.teemo_backend.Service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/production")
public class ProductionController {

    private final ProductionService productionService;


    @PostMapping()
    public ResponseEntity<String> saveOneProduction(@RequestBody ConsumptionRequest consumption){

        productionService.saveProduction(consumption);

        return ResponseEntity.ok().body("성공");
    }

    @GetMapping()
    public List<Production> getProductionList(@RequestParam("id") long id){



        List<Production> list =productionService.getList(id);

        return list;
    }




}
