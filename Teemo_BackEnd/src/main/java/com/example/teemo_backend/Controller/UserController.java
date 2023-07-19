package com.example.teemo_backend.Controller;


import com.example.teemo_backend.Domain.Entity.User;
import com.example.teemo_backend.Exception.AppException;
import com.example.teemo_backend.Exception.ErrorCode;
import com.example.teemo_backend.Repository.UserRepository;
import com.example.teemo_backend.Service.ArticleService;
import com.example.teemo_backend.Domain.Dto.JwtToken;
import com.example.teemo_backend.Domain.Dto.UserJoinRequest;
import com.example.teemo_backend.Domain.Dto.UserLoginRequest;
import com.example.teemo_backend.Service.UserService;
import com.example.teemo_backend.Utils.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.config.Elements.JWT;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {


    private final UserService userService;


    @PostMapping(value = "/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto) {

        userService.join(dto);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserLoginRequest dto) {

        JwtToken token = userService.login(dto.getUserEmail(), dto.getPassword());

        return ResponseEntity.ok().body(token);
    }

    @GetMapping(value = "/nickname-duplicate")
    public ResponseEntity<String> checkNickname(@RequestBody String nickname) {

        String result = userService.checkNickname(nickname);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/reissue")
    public ResponseEntity<JwtToken> reissue(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JwtToken jwtToken = userService.reissueToken( request,  response);


        return ResponseEntity.ok().body(jwtToken);
    }

}





