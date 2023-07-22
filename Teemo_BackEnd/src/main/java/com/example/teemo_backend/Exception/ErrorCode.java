package com.example.teemo_backend.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USEREMAIL_DUPLICATED(HttpStatus.CONFLICT,""), // 회원 가입시 이메일 중복
    USEREMAIL_NOT_FOUND(HttpStatus.NOT_FOUND,""), // 로그인시 이메일 없음
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"") ,// 로그인시 비밀번호 오류
    NICKNAME_DUPLICATED(HttpStatus.CONFLICT,""),// 닉네임 중복
    PASSWORD_NOT_ASSOCIATE(HttpStatus.CONFLICT,""),// 비밀번호 변경시 새 비밀번호와 확인용 비밀번호 불일치
    JWT_REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,""),
    JWT_REFRESH_TOKEN_MISSING(HttpStatus.UNAUTHORIZED,"")
    ;

    private HttpStatus httpStatus;
    private String message;
}
