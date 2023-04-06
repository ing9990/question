package com.question.commons.error.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private Object error;
    private Integer errorCode;

    public static ErrorResponse of(
            Error... args
    ) {
        return ErrorResponse.builder()
                .error(List.of(args))
                .errorCode(400)
                .build();
    }

    public static ErrorResponse of(
            Integer errorCode,
            Error... args) {
        return ErrorResponse.builder()
                .error(List.of(args))
                .errorCode(errorCode)
                .build();
    }

    public static ErrorResponse of(
            HttpStatus status,
            Error... args
    ) {
        return ErrorResponse.builder()
                .error(List.of(args))
                .errorCode(status.value())
                .build();
    }

    public static ErrorResponse of(
            List<Error> args,
            HttpStatus status
    ) {
        return ErrorResponse.builder()
                .error(args)
                .errorCode(status.value())
                .build();
    }
}
