package com.question.manage.application;

import java.util.List;

import com.question.manage.domain.ReportType;
import com.question.user.io.response.UserResponse;

public interface ReportService {

	String REPORT_MESSAGE_FORMAT_1 = "%s님은 %s시 경 %s 행위로 인한 신고가 접수되었습니다. \n지나친 %s는 계정 이용 정지 조치를 받을 수 있습니다.";
	String REPORT_MESSAGE_FORMAT_2 = "%s님은 %s시 경 %s와 %s행위로 인한 신고가 접수되었습니다. \n반복된 %s로 인해 계정 이용 제한 조치를 받았습니다. \n자세한 내용은 이메일을 확인해주세요.";

	// 신고 누적으로 인한 제재 대상자 목록
	List<UserResponse> getSubject2Sanctions();

	// 신고 누적으로 인한 경고 조치 알림 대상자 목록
	List<UserResponse> getSubject2Notifications();

	void report(String whistleBlowerId, String reportedUserId, String reason, ReportType reportType);
}
