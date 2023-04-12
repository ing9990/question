package com.question.manage.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.question.manage.domain.Report;
import com.question.manage.domain.ReportRepository;
import com.question.manage.domain.ReportType;
import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

// 신고 1회 즉시 경고 3회 중첩 시 정지
@Service
@Primary
@RequiredArgsConstructor
public class StrongReportService implements ReportService {

	private final ReportRepository reportRepository;
	private final UserRepository userRepository;

	@Value("${manage.report.stack.strong.notification}")
	private Integer notificationStack;

	@Value("${manage.report.stack.strong.sanctions}")
	private Integer sanctionsStack;

	@Override
	public List<Report> getSubject2Sanctions() {
		return reportRepository.getSubject2Sanctions(sanctionsStack);
	}

	@Override
	public List<Report> getSubject2Notifications() {
		return reportRepository.getSubject2Sanctions(notificationStack);
	}

	@Override
	public void report(String whistleBlowerId, String reportedUserId, String reason, ReportType reportType) {
		User whistleBower = userRepository.findById(whistleBlowerId)
			.orElseThrow(UserNotFoundException::new);

		User reportedUser = userRepository.findById(reportedUserId)
			.orElseThrow(UserNotFoundException::new);

		reportRepository.save(new Report(whistleBower, reportedUser, reason, reportType));
	}
}