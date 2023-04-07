package com.question.watchlist.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.commons.BaseResponse;
import com.question.infra.in.aop.support.CurrentUser;
import com.question.watchlist.application.WatchlistService;
import com.question.watchlist.io.request.MakeWatchlistRequest;
import com.question.watchlist.io.response.WatchlistResponse;

@RestController
@RequestMapping("/api/watchlists")
public class WatchlistApi {

	private final WatchlistService watchlistService;

	public WatchlistApi(
		final WatchlistService watchlistService
	) {
		this.watchlistService = watchlistService;
	}

	@GetMapping
	public ResponseEntity<BaseResponse> getWatchlist(
		@CurrentUser final String userId
	) {
		List<WatchlistResponse> watchlistResponse = watchlistService.getWatchlist(userId);

		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponse.ok(watchlistResponse, "관심목록을 응답합니다."));
	}

	/**
	 * 새로운 관심목록을 만듭니다.
	 * @param userId
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Void> newWatchlist(
		@CurrentUser final String userId,
		@Valid @RequestBody MakeWatchlistRequest makeWatchlistRequest
	) {
		watchlistService.newWatchlist(userId, makeWatchlistRequest.getName(), makeWatchlistRequest.getDescription());

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * 관심목록에 질문을 추가합니다.
	 * @param questionId
	 * @param userId
	 * @return
	 */
	@PostMapping("/{watchlistId}/{questionId}")
	public ResponseEntity<Void> addWatchlist(
		@PathVariable final Long watchlistId,
		@PathVariable final Long questionId,
		@CurrentUser final String userId
	) {
		watchlistService.addWatchlist(userId, watchlistId, questionId);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
