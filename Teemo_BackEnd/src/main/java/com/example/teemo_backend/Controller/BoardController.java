package com.example.teemo_backend.Controller;

import com.example.teemo_backend.Domain.Dto.UserJoinRequest;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="board")
public class BoardController {

    @GetMapping (value = "/write")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto){



        return ResponseEntity.ok().body("글쓰기 성공");
    }
}
