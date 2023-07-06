package com.example.teemo_backend.Service;


import com.example.teemo_backend.Config.PrincipalDetailService;
import com.example.teemo_backend.Domain.Dto.JwtToken;
import com.example.teemo_backend.Domain.Dto.UserJoinRequest;
import com.example.teemo_backend.Exception.AppException;
import com.example.teemo_backend.Exception.ErrorCode;
import com.example.teemo_backend.Repository.UserRepository;
import com.example.teemo_backend.Utils.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.teemo_backend.Domain.Entity.User;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PrincipalDetailService PrincipalDetailService;
    private final BCryptPasswordEncoder encoder;



    @Value("${jwt.token.secret}")
    private String key ;
    private long expireTimeToMs = 1000*60*60l; // 토큰 expireTime 1시간

    @Transactional
    public String join(UserJoinRequest dto){

        // 이메일 중복 체크
        userRepository.findByEmail(dto.getUserEmail())
                .ifPresent(user -> {throw new AppException(ErrorCode.USEREMAIL_DUPLICATED,"이미 존재하는 이메일");});

        User user = User.builder()
                .email(dto.getUserEmail())
                .password(encoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .build();

        userRepository.save(user);

        return "succcess";
    }

    public JwtToken login(String email, String password){

        //email 없음
        User findUser= userRepository.findByEmail(email)
                .orElseThrow(()->new AppException(ErrorCode.USEREMAIL_NOT_FOUND,email+"존재하지 않는 이메일"));

        //비밀번호 일치 판단
        if (true != encoder.matches(password,findUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD,"비밀번호가 잘못입력되었습니다");
        }

        UserDetails sessionUser = PrincipalDetailService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password,sessionUser.getAuthorities());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtToken token = jwtTokenProvider.generateToken(authentication,email);

        return token;
    }

    public String checkNickname(String nickname) {

        userRepository.findByNickname(nickname)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.NICKNAME_DUPLICATED, "이미 존재하는 닉네임");
                });

        return "사용가능한 닉네임입니다";
    }

//    public String checkPassword(String email,String password){
//
//        User findUser= userRepository.findByEmail(email)
//                .orElseThrow(()->new AppException(ErrorCode.USEREMAIL_NOT_FOUND,email+"존재하지 않는 이메일"));
//
//
//        if (true != encoder.matches(password,findUser.getPassword())) {
//            throw new AppException(ErrorCode.INVALID_PASSWORD,"비밀번호가 일치하지 않음");
//        }
//
//        return "비밀번호 일치";
//    }

    public boolean checkPassword(String token,String password){

        String email = jwtTokenProvider.extractEmailFromJWT(token);

        User findUser= userRepository.findByEmail(email)
                .orElseThrow(()->new AppException(ErrorCode.USEREMAIL_NOT_FOUND,email+"존재하지 않는 이메일"));


        if (true != encoder.matches(password,findUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD,"비밀번호가 일치하지 않음");
        }

        return true;
    }

    @Transactional
    public String changePassword(String token,String password){

        String email = jwtTokenProvider.extractEmailFromJWT(token);

        User findUser= userRepository.findByEmail(email)
                .orElseThrow(()->new AppException(ErrorCode.USEREMAIL_NOT_FOUND,email+"존재하지 않는 이메일"));


        String enPw = encoder.encode(password);


        findUser.setPassword(enPw);

        return "비밀번호가 변경되었습니다";
    }

}
