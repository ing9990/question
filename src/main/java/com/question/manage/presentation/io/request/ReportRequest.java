package com.question.manage.presentation.io.request;

import com.question.manage.domain.ReportType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportRequest {
	private String reason;

	private ReportType reportType;
}
