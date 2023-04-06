package com.question.infra.in;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig {

    private static final int CORE_POOL_SIZE = 15;
    private static final int MAX_POOL_SIZE = 25;
    private static final int QUEUE_CAPACITY = Integer.MAX_VALUE;
    private static final String THREAD_NAME_PREFIX = "event-s";

    @Bean(name = "event-executor")
    public Executor rpcExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.initialize();

        return executor;
    }
}
