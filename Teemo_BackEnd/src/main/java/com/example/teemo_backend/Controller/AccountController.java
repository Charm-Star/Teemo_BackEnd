package com.example.teemo_backend.Controller;


import com.example.teemo_backend.Service.UserService;
import com.example.teemo_backend.Utils.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/account")
public class AccountController {

    UserService userService;



    //비밀번호 일치 확인
    @PostMapping(value = "/pw-check")
    public ResponseEntity<String> checkPassword(HttpServletRequest request,@RequestBody String password){

        String token = request.getHeader("Authorization");

        userService.checkPassword(token,password);

        return ResponseEntity.ok().body("비밀번호 일치");
    }

    //비밀번호 변경
    @PatchMapping(value = "/pw-change")
    public ResponseEntity<String> changePassword(HttpServletRequest request,@RequestBody String password){

        String token  = request.getHeader("Authorization");
        userService.changePassword(token,password);


        return ResponseEntity.ok().body("비밀번호가 변경되었습니다");
    }


}
