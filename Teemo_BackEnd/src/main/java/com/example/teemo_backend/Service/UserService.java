package com.example.teemo_backend.Service;


import com.example.teemo_backend.Config.PrincipalDetailService;
import com.example.teemo_backend.Domain.Dto.ChangePwRequest;
import com.example.teemo_backend.Domain.Dto.JwtToken;
import com.example.teemo_backend.Domain.Dto.RefreshToken;
import com.example.teemo_backend.Domain.Dto.UserJoinRequest;
import com.example.teemo_backend.Exception.AppException;
import com.example.teemo_backend.Exception.ErrorCode;
import com.example.teemo_backend.Repository.UserRepository;
import com.example.teemo_backend.Utils.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

import java.util.HashMap;
import java.util.Optional;


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

        JwtToken token = jwtTokenProvider.generateToken(authentication,findUser.getEmail(),findUser.getId());

        return token;
    }

    public String checkNickname(String nickname) {
        System.out.println(nickname);

        userRepository.findByNickname(nickname)
                .ifPresent(user -> {
                    System.out.println(user.getNickname());

                    throw new AppException(ErrorCode.NICKNAME_DUPLICATED, "이미 존재하는 닉네임");
                });

        return "사용가능한 닉네임입니다";
    }


    @Transactional
    public User changePassword(String token, ChangePwRequest changePwRequest){

        String email = jwtTokenProvider.extractEmailFromJWT(token);
        String ExPassword = changePwRequest.getExPassword();
        String newPassword = changePwRequest.getNewPassword();
        String newPasswordCheck = changePwRequest.getNewPasswordCheck();

        User findUser= userRepository.findByEmail(email)
                .orElseThrow(()->new AppException(ErrorCode.USEREMAIL_NOT_FOUND,email+"존재하지 않는 이메일"));

        if (true != encoder.matches(ExPassword,findUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD,"비밀번호가 일치하지 않음");
        }

        if(!newPassword.equals(newPasswordCheck)){
            throw new AppException(ErrorCode.PASSWORD_NOT_ASSOCIATE,"새로운 비밀번호가 일치하지 않음");
        }

        String enPw = encoder.encode(newPassword);

        // 확인 해보기
        findUser.setPassword(enPw);

        return findUser;
    }
    public JwtToken reissueToken(HttpServletRequest request, HttpServletResponse response){
        try {
            String token = request.getHeader("Authorization");
            // 만료된 Access Token을 디코딩하여 Payload 값을 가져옴
            HashMap<String, String> payloadMap = JwtTokenProvider.getPayloadByToken(token);
            String email = payloadMap.get("sub");
            long id = Long.parseLong(payloadMap.get("id"));
            // Redis에 저장된 Refresh Token을 찾고 만일 없다면 401 에러를 내려줍니다
            Optional<RefreshToken> refreshToken = redisRepository.findByEmail(email);
            refreshToken.orElseThrow(
                    () -> new AppException(ErrorCode.JWT_REFRESH_TOKEN_MISSING,"")
            );

            // Refresh Token이 만료가 된 토큰인지 확인합니다
            boolean isTokenValid = JwtTokenProvider.validateToken(refreshToken.get().getToken());

            // Refresh Token이 만료가 되지 않은 경우
            if(isTokenValid) {
                Optional<User> member = userRepository.findByEmail(email);

                if(member.isPresent()) {
                    // Access Token과 Refresh Token을 둘 다 새로 발급하여 Refresh Token은 새로 Redis에 저장
                    UserDetails sessionUser = PrincipalDetailService.loadUserByUsername(email);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,sessionUser.getAuthorities());

                    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

                    JwtToken jwtToken = jwtTokenProvider.generateToken(authentication,email,id);



                    redisRepository.save(newRefreshToken);

                    return jwtToken;
                }
            }
        } catch(ExpiredJwtException e) {
            // Refresh Token 만료 Exception
            throw new AppException(ErrorCode.JWT_REFRESH_TOKEN_EXPIRED,"");
        }
        return null;
    }

}
