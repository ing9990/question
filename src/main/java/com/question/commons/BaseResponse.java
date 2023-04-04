package com.question.commons;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse {

    private Object data;
    private String message;

    public static BaseResponse ok(
            Object data,
            String message
    ) {
        return new BaseResponse(data, message);
    }
}
