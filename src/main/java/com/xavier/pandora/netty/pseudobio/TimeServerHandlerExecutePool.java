package com.xavier.pandora.netty.pseudobio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {
    public static final Logger logger = LoggerFactory.getLogger(TimeServerHandlerExecutePool.class);
    private ExecutorService executor;
    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
        //corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue
        logger.info("Available Threads: " + Runtime.getRuntime().availableProcessors());
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }
    public void execute(Runnable task) {
        logger.info(String.valueOf(System.identityHashCode(task)));
        executor.execute(task);
    }
}
