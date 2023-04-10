package com.question.manage.presentation;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.infra.in.aop.support.CurrentUser;
import com.question.manage.application.ReportService;
import com.question.manage.io.request.ReportRequest;

@RestController
@RequestMapping("/api/report")
public class ReportApi {

	private final ReportService reportService;

	public ReportApi(final ReportService reportService) {
		this.reportService = reportService;
	}

	@PostMapping("/{reportedUserId}")
	public ResponseEntity<Void> report(
		@PathVariable String reportedUserId,
		@CurrentUser String whistleBlowerId,
		@Valid @RequestBody ReportRequest reportRequest
	) {
		reportService.report(whistleBlowerId, reportedUserId, reportRequest.getReason(), reportRequest.getReportType());

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
