package com.question.question.io.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQuestionRequest {

    @NotEmpty(message = "title cannot be null")
    private String title;

    @NotEmpty(message = "detail caonnot be null")
    private String detail;
}
