package com.question.manage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import com.question.commons.BaseTimeEntity;
import com.question.user.domain.User;

@Entity
@Table(name = "user_report")
public class Report extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id")
	private Long reportId;

	@Column(name = "reason", nullable = false, updatable = false)
	private String reason;

	@OneToOne
	@JoinColumn(name = "whistle_blower", nullable = false, updatable = false)
	private User whistleBlower;

	@OneToOne
	@JoinColumn(name = "reported_user", nullable = false, updatable = false)
	private User reported;

	@Enumerated(EnumType.STRING)
	@Column(name = "reflect", nullable = false)
	private Reflect reflect;

	@Enumerated(EnumType.STRING)
	@Column(name = "report_type", nullable = false, updatable = false)
	private ReportType reportType;

	protected Report() {

	}

	public Report(
		final User whistleBlower,
		final User reported,
		final String reason,
		final ReportType reportType
	) {
		validateUser(whistleBlower, reported);
		validateReason(reason);

		this.whistleBlower = whistleBlower;
		this.reported = reported;

		this.reason = reason;
		this.reflect = Reflect.WAIT;
		this.reportType = reportType;
	}

	private void validateUser(User whistleBlower, User reported) {
		if (whistleBlower == null || reported == null || whistleBlower == reported) {
			throw new IllegalArgumentException("신고자 또는 피 신고자가 빈 값입니다.");
		}
	}

	private void validateReason(final String reason) {
		if (!StringUtils.hasText(reason)) {
			throw new IllegalArgumentException("신고 사유는 필수 값입니다.");
		}
	}

	public Reflect getReflect() {
		return reflect;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public Long getReportId() {
		return reportId;
	}

	public String getReason() {
		return reason;
	}

	public User getReported() {
		return reported;
	}
}
