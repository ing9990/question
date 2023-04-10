package com.question.manage.application;

import java.util.List;

import com.question.user.io.response.UserResponse;

// 신고 1회 즉시 경고 3회 중첩 시 정지
public class StrongReportService implements ReportService {
	@Override
	public List<UserResponse> getSubject2Sanctions() {
		return null;
	}

	@Override
	public List<UserResponse> getSubject2Notifications() {
		return null;
	}
}
