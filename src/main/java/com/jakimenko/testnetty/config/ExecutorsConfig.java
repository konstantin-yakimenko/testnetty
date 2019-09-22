package com.jakimenko.testnetty.config;

/**
 * @author konst
 */
public class ExecutorsConfig {
}


/*
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

//@Configuration
//@EnableScheduling
//@EnableAsync
public class ExecutorsConfig {// implements SchedulingConfigurer, AsyncConfigurer {
    private static final Integer SCHEDULER_POOL_SIZE = 5;
    private static final Integer ASYNC_POOL_SIZE = 10;


//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();// taskScheduler
//        taskScheduler.setPoolSize(SCHEDULER_POOL_SIZE);
//        taskScheduler.initialize();
//        taskScheduler.setThreadNamePrefix("ScheduledExecutor-");
//        taskRegistrar.setTaskScheduler(taskScheduler);
//    }
//
//    @Override
//    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(ASYNC_POOL_SIZE);
//        executor.setThreadNamePrefix("AsyncExecutor-");
//        executor.initialize();
//        return executor;
//    }
//
//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return (ex, method, params) -> LoggerFactory.getLogger("Async").error("Async error", ex);
//    }
}
 */