package com.question.manage.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	// Reflect가 WAIT 인 신고 내역 중 3회 이상 신고당한 유저를 검색
}
