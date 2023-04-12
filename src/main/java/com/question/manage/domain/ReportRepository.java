package com.question.manage.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	@Query("select re from Report re group by re.reported having count(re) > ?1")
	List<Report> getSubject2Sanctions(int count);

	// Reflect가 WAIT 인 신고 내역 중 3회 이상 신고당한 유저를 검색
}
