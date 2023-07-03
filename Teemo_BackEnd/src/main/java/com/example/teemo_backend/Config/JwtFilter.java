//package com.example.teemo_backend.Config;
//
//import com.example.teemo_backend.Service.UserService;
//import com.example.teemo_backend.Utils.JwtTokenUtil;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.security.Security;
//import java.util.Date;
//import java.util.List;
//
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//
//    private final UserService userService;
//    private final String secretKey;
//    private final JwtTokenUtil jwtTokenUtil;
//
//
//    public JwtFilter(JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        final String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
//        System.out.println(auth);
//
//        if (auth == null||!auth.startsWith("Bearer ")){
//            filterChain.doFilter(request,response);
//            System.out.println("auth가 없음");
//            return ;
//        }
//
//        // 토큰 꺼내기
//        String token  = auth.split(" ")[1];
//
//        //expire  확인하기
//
//        if(JwtTokenUtil.isExpired(token,secretKey)){
//            filterChain.doFilter(request,response);
//            System.out.println("token이 만료 되었습니다");
//            return ;
//        }
//
//        String userEmail = JwtTokenUtil.getUseremail(token,secretKey);
//
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userEmail,null, List.of(new SimpleGrantedAuthority("USER")));
//
//        authenticationToken.setDetails((new WebAuthenticationDetailsSource().buildDetails(request)));
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        filterChain.doFilter(request,response);
//
//
//    }
//}
