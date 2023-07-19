package com.example.teemo_backend.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/production")
public class ProductionController {


    @PostMapping(value = "/consumption-activities")
    public ResponseEntity<String> checkNickname(@RequestBody long id){






        return ResponseEntity.ok().body("");
    }


}
