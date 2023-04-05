package com.question.question.io.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDetailRequest {

    @NotEmpty(message = "detail cannot be null")
    private String detail;

}
