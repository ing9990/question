package com.question.commons;

public enum NotificationFormat {

	REPORT_MESSAGE_FORMAT_1("%s님은 %s시 경 %s 행위로 인한 신고가 접수되었습니다. \n지나친 %s는 계정 이용 정지 조치를 받을 수 있습니다."),
	REPORT_MESSAGE_FORMAT_2(
		"%s님은 %s시 경 %s와 %s행위로 인한 신고가 접수되었습니다. \n반복된 %s로 인해 계정 이용 제한 조치를 받았습니다. \n자세한 내용은 이메일을 확인해주세요."),
	ADD_WATCHLIST_FORMAT("%s님이 [%s]을 관심목록에 추가했어요.");;

	public String format;

	NotificationFormat(String format) {
		this.format = format;
	}

	public String getFormat() {
		return this.format;
	}
}