package com.question.question.presentation.io.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDetailRequest {

	@NotEmpty(message = "detail cannot be null")
	private String detail;

}
