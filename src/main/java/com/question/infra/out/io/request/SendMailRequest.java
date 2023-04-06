package com.question.infra.out.io.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMailRequest {

    private String to;

    private String from;

    private String title;

    private String content;
}
