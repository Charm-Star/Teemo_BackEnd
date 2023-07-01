package com.example.teemo_backend.Controller;


import com.example.teemo_backend.Domain.Dto.UserJoinRequest;
import com.example.teemo_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    @PostMapping(value = "/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto){


        userService.join(dto.getUserEmail() , dto.getPassword());
        return ResponseEntity.ok().body("회원가입 성공");
    }

}