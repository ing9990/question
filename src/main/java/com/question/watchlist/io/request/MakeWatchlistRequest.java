package com.question.watchlist.io.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakeWatchlistRequest {

	@NotEmpty(message = "watchlist name cannot be null.")
	@Size(min = 3, max = 24)
	private String name;

	@NotNull(message = "watchlist description cannot be null.")
	private String description;
}
