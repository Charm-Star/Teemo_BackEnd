package com.example.teemo_backend.Controller;


import lombok.Getter;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {
    @PostMapping(value = "")
    public String writePost(){
        return "";
    }

    @GetMapping(value = "/{id}")
    public String getPost(@PathVariable("id") Long id){

        return "";
    }

    @DeleteMapping(value = "{id}")
    public String deletePost(@PathVariable("id") long id){
        return"";
    }
    @PatchMapping(value = "{id}")
    public String updatePost(@PathVariable("id") long id){
        return"";
    }

}