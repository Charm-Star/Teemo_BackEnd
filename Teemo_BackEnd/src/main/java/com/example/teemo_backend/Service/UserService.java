package com.example.teemo_backend.Service;


import com.example.teemo_backend.Exception.AppException;
import com.example.teemo_backend.Exception.ErrorCode;
import com.example.teemo_backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.teemo_backend.Domain.Dto.User;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    public String join(String userEmail , String password){

        // 이메일 중복 체크
        userRepository.findByEmail(userEmail)
                .ifPresent(user -> {throw new AppException(ErrorCode.USEREMAIL_DUPLICATED,"이미 존재하는 이메일");});

        User user = User.builder()
                .email(userEmail)
                .password(password)
                .build();

        userRepository.save(user);

        return "succcess";
    }
}
