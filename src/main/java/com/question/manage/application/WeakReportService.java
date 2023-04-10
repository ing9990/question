package com.question.manage.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.question.user.io.response.UserResponse;

// 신고 3회 중첩 시 경고 5회 중첩 시 정지
@Service
public class WeakReportService implements ReportService {

	@Override
	public List<UserResponse> getSubject2Sanctions() {
		return null;
	}

	@Override
	public List<UserResponse> getSubject2Notifications() {
		return null;
	}
}
