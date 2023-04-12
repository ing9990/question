package com.question.manage;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import com.question.commons.NotificationFormat;
import com.question.infra.out.io.NotificationEvent;
import com.question.infra.out.io.NotificationType;
import com.question.manage.application.ReportService;
import com.question.manage.domain.Report;
import com.question.manage.domain.ReportType;
import com.question.manage.domain.ReportTypeResolver;
import com.question.user.domain.User;

public class SanctionsScheduler {

	private final ApplicationEventPublisher publisher;
	private final ReportService reportService;
	private final ReportTypeResolver reportTypeResolver;

	public SanctionsScheduler(
		final ApplicationEventPublisher publisher,
		final ReportService reportService,
		ReportTypeResolver reportTypeResolver) {

		this.publisher = publisher;
		this.reportService = reportService;
		this.reportTypeResolver = reportTypeResolver;
	}

	@Async
	@Scheduled(fixedDelay = 1000 * 60 * 30)
	public void apply() {
		reportService.getSubject2Sanctions().forEach((report) -> publisher.publishEvent(
				new NotificationEvent(this,
					NotificationType.PUSH,
					getSanctionsMessage(report),
					"",
					report.getReported().getEmail())
			)
		);

		reportService.getSubject2Notifications().forEach(report -> publisher.publishEvent(
			new NotificationEvent(this,
				NotificationType.EMAIL,
				getNotificationMessage(report),
				"",
				report.getReported().getEmail()
			)
		));
	}

	private String getNotificationMessage(Report report) {
		ReportType allegation = report.getReportType();
		User reported = report.getReported();
		LocalDateTime time = report.getCreatedTime();

		return String.format(NotificationFormat.REPORT_MESSAGE_FORMAT_1.getFormat(),
			reported.getUsername(),
			time.getHour() + "시 " + time.getMinute() + "분",
			reportTypeResolver.resolve(allegation)
		);
	}

	private String getSanctionsMessage(Report report) {
		String allegation = reportTypeResolver.resolve(report.getReportType());
		User reported = report.getReported();
		LocalDateTime time = report.getCreatedTime();

		return String.format(NotificationFormat.REPORT_MESSAGE_FORMAT_2.getFormat(),
			reported.getUsername(),
			time.getHour() + "시 " + time.getMinute() + "분",
			allegation, allegation
		);
	}

}
