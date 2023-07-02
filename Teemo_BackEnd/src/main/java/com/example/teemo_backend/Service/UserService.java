package com.example.teemo_backend.Service;


import com.example.teemo_backend.Exception.AppException;
import com.example.teemo_backend.Exception.ErrorCode;
import com.example.teemo_backend.Repository.UserRepository;
import com.example.teemo_backend.Utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.teemo_backend.Domain.Entity.User;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    @Value("${jwt.token.secret}")
    private String key ;
    private long expireTimeToMs = 1000*60*60l; // 토큰 expireTime 1시간
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

    public String login(String email, String password){


        //email 없음
        User findUser= userRepository.findByEmail(email)
                .orElseThrow(()->new AppException(ErrorCode.USEREMAIL_NOT_FOUND,email+"존재하지 않는 이메일"));

        System.out.println(findUser.getEmail());

        //password 틀림
        if(!findUser.getPassword().equals(password)){
            throw new AppException(ErrorCode.INVALID_PASSWORD,"비밀번호가 잘못입력되었습니다");
        }



        // 토큰 발행
        String token = JwtTokenUtil.create(findUser.getEmail(),key,expireTimeToMs);


        return token;
    }

}
