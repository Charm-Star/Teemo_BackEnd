package com.example.teemo_backend.Config;

import com.example.teemo_backend.Config.JwtFilter;
import com.example.teemo_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final UserService userService;
    @Value("${jwt.token.secret}")
    private String secretKey;


    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .httpBasic(httpBasicConfigurer->httpBasicConfigurer.disable())
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests()
                .requestMatchers("user/join" , "user/login").permitAll()
                .and()
                .addFilterBefore(
                        new JwtFilter(userService,secretKey),
                        UsernamePasswordAuthenticationFilter.class
                );
    }
}