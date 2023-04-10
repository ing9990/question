package com.question.manage.application;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.question.manage.domain.Report;
import com.question.manage.domain.ReportRepository;
import com.question.manage.domain.ReportType;
import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;
import com.question.user.io.response.UserResponse;

import lombok.RequiredArgsConstructor;

// 신고 1회 즉시 경고 3회 중첩 시 정지
@Service
@Primary
@RequiredArgsConstructor
public class StrongReportService implements ReportService {

	private final ReportRepository reportRepository;
	private final UserRepository userRepository;

	@Override
	public List<UserResponse> getSubject2Sanctions() {
		return null;
	}

	@Override
	public List<UserResponse> getSubject2Notifications() {
		return null;
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