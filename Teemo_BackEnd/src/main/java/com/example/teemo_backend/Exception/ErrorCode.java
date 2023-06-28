package com.example.teemo_backend.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USEREMAIL_DUPLICATED(HttpStatus.CONFLICT,"");

    private HttpStatus httpStatus;
    private String message;
}
