package com.question.commons;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract public class BaseTimeEntity {

	@CreatedDate
	@Column(name = "created_time", updatable = false, nullable = false)
	protected LocalDateTime createdTime;

	@LastModifiedDate
	@Column(name = "modified_time", nullable = false)
	protected LocalDateTime modifiedTime;
}
