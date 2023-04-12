package com.question.manage.domain;

import org.springframework.stereotype.Component;

@Component
public class ReportTypeResolver {

	public String resolve(ReportType allegation) {
		switch (allegation) {
			case DEFAMATORY_REMARKS:
				return "모욕적인 발언";
			case PORNOGRAPHIC_MATERIAL:
				return "음란물 게시";
			case SPAMMING:
				return "도배 및 스팸";
			default:
				throw new IllegalStateException();
		}
	}
}
