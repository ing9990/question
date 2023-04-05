package com.question.commons;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse {

    private HttpStatus status;
    private Object data;
    private String message;

    public static BaseResponse ok(
            Object data,
            String message
    ) {
        return new BaseResponse(HttpStatus.OK, data, message);
    }

    public static BaseResponse of(
            HttpStatus status,
            Object data,
            String message
    ) {
        return new BaseResponse(status, data, message);
    }
}
