package com.question.manage.application;

import java.util.List;

import com.question.manage.domain.Report;
import com.question.manage.domain.ReportType;

public interface ReportService {

	// 신고 누적으로 인한 제재 대상자 목록
	List<Report> getSubject2Sanctions();

	// 신고 누적으로 인한 경고 조치 알림 대상자 목록
	List<Report> getSubject2Notifications();

	void report(String whistleBlowerId, String reportedUserId, String reason, ReportType reportType);
}
