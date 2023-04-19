package com.question.question.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	// 질문글의 답변이 N개 이상인 답변만 가져옵니다.
	@Query("SELECT q FROM Question q WHERE SIZE(q.answers) >= ?1")
	List<Question> findQuestionsByAnswersSize(int count);

}
