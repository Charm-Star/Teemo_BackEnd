package com.example.teemo_backend.Controller;


import com.example.teemo_backend.Domain.Dto.WriteBoardRequest;
import com.example.teemo_backend.Domain.Entity.Board;
import com.example.teemo_backend.Service.BoardService;
import lombok.Getter;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<String> writePost(@RequestBody WriteBoardRequest writeBoardRequest){

        boardService.writeBoard(writeBoardRequest);


        return ResponseEntity.ok().body("성공");
    }

    @GetMapping
    public List<Board> getPost(){

        List<Board> list = boardService.getBoardList();

        return list;
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){

        boardService.deleteBodard(id);

        return ResponseEntity.ok().body("삭제 성공");
    }
    @PatchMapping(value = "{id}")
    public Board updatePost(@PathVariable("id") long id,@RequestBody WriteBoardRequest writeBoardRequest){
        Board board = boardService.update(id,writeBoardRequest);

        return board;
    }

}