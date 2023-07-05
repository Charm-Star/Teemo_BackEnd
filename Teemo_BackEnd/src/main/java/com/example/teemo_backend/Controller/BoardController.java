package com.example.teemo_backend.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {
    @PostMapping(value = "/test")
    public String login(){






        return "";
    }

}