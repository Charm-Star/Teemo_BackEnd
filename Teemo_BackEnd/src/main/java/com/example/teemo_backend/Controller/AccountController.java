package com.example.teemo_backend.Controller;


import com.example.teemo_backend.Service.UserService;
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
    public ResponseEntity<String> checkPassword(){




        return ResponseEntity.ok().body("비밀번호 일치");
    }

    //비밀번호 변경
    @PatchMapping(value = "/pw-change")
    public ResponseEntity<String> changePassword(){




        return ResponseEntity.ok().body("비밀번호가 변경되었습니다");
    }


}
