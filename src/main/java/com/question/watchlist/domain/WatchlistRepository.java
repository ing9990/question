package com.question.watchlist.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.question.user.domain.User;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

	@Query("select w from Watchlist w where w.creator = ?1")
	List<Watchlist> findWatchlistByCreator(User creator);

	@Query("select w from Watchlist w where w.creator.userId = ?1")
	List<Watchlist> findWatchlistByCreatorId(String creatorId);

}
