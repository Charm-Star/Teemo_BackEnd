package com.example.teemo_backend.Service;


import com.example.teemo_backend.Domain.Dto.WriteBoardRequest;
import com.example.teemo_backend.Domain.Entity.Board;
import com.example.teemo_backend.Domain.Entity.User;
import com.example.teemo_backend.Exception.AppException;
import com.example.teemo_backend.Exception.ErrorCode;
import com.example.teemo_backend.Repository.BoardRepository;
import com.example.teemo_backend.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public String writeBoard(WriteBoardRequest writeBoardRequest){

        long userId = writeBoardRequest.getUserId();

        User findUser= userRepository.findById(userId)
                .orElseThrow(()->new AppException(ErrorCode.USEREMAIL_NOT_FOUND,"존재하지 않은 유저"));


        Board board = Board.builder()
                .title(writeBoardRequest.getTitle())
                .content(writeBoardRequest.getContent())
                .userId(writeBoardRequest.getUserId())
                .date(new Date())
                .build();

        boardRepository.save(board);


        return "글쓰기 성공";
    }

    public List<Board> getBoardList() {

        List<Board> list = boardRepository.findAll();

        return list;
    }

    public void deleteBodard(long id) {

        boardRepository.deleteById(id);
    }

    @Transactional
    public Board update(long id, WriteBoardRequest writeBoardRequest) {
        Optional<Board> exBoard = boardRepository.findById(id);

        if(exBoard.isEmpty()){
            throw new RuntimeException();
        }

        Board board = exBoard.get();

        board.setContent(writeBoardRequest.getContent());
        board.setTitle(writeBoardRequest.getTitle());
        board.setDate(new Date());

        return board;


    }
}
