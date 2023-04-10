package com.question.watchlist.domain;

public class WatchlistNotFoundException extends RuntimeException {

	public WatchlistNotFoundException() {
		this("없는 관심목록입니다.");
	}

	public WatchlistNotFoundException(String message) {
		super(message);
	}
}
