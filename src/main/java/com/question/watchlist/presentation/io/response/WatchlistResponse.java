package com.question.watchlist.presentation.io.response;

import java.util.List;
import java.util.stream.Collectors;

import com.question.question.presentation.io.response.QuestionResponse;
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

	private String watchListName;

	private String watchListDescription;

	private List<QuestionResponse> questionList;

	public static WatchlistResponse from(final Watchlist watchlist) {
		return WatchlistResponse.builder()
			.creatorId(watchlist.getCreator().getUserId())
			.creatorName(watchlist.getCreator().getUsername())
			.watchListName(watchlist.getName())
			.watchListDescription(watchlist.getDescription())
			.questionList(watchlist.getQuestionList()
				.stream().map(QuestionResponse::of)
				.collect(Collectors.toList()))
			.build();
	}
}
