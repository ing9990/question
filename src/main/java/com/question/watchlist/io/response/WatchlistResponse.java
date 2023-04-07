package com.question.watchlist.io.response;

import java.util.List;
import java.util.stream.Collectors;

import com.question.question.io.response.QuestionResponse;
import com.question.watchlist.domain.Watchlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WatchlistResponse {

	private String creatorId;

	private String creatorName;

	private List<QuestionResponse> questionList;

	public static WatchlistResponse from(final Watchlist watchlist) {
		return WatchlistResponse.builder()
			.creatorId(watchlist.getCreator().getUserId())
			.creatorName(watchlist.getCreator().getUsername())
			.questionList(watchlist.getQuestionList()
				.stream().map(QuestionResponse::of)
				.collect(Collectors.toList()))
			.build();
	}
}
