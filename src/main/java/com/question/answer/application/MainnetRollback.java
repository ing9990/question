package com.question.answer.application;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MainnetTransaction extends TransactionSynchronizationAdapter {

	@Override
	public int getOrder() {
		return super.getOrder();
	}

	@Override
	public void suspend() {
		super.suspend();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void flush() {
		super.flush();
	}

	@Override
	public void beforeCommit(boolean readOnly) {
		super.beforeCommit(readOnly);
	}

	@Override
	public void beforeCompletion() {
		super.beforeCompletion();
	}

	@Override
	public void afterCommit() {
		super.afterCommit();
	}

	@Override
	public void afterCompletion(int status) {
		super.afterCompletion(status);
	}
}
