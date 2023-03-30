package com.example.demo.utils.thread;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.*;

public class ExServUtils {
    // 每个任务，都有自己单独的线程池
    private static Map<String, ExecutorService> executors = new ConcurrentHashMap<>();

    // 初始化一个线程池
    private static ExecutorService init(String poolName, int poolSize) {
        return new ThreadPoolExecutor(poolSize, poolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("Pzc-" + poolName).setDaemon(false).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    // 获取线程池
    public static ExecutorService getOrInitExecutors(String threadName,int threadSize) {
        ExecutorService executorService = executors.get(threadName);
        if (null == executorService) {
            synchronized (ExServUtils.class) {
                executorService = executors.get(threadName);
                if (null == executorService) {
                    executorService = init(threadName, threadSize);
                    executors.put(threadName, executorService);
                }
            }
        }
        return executorService;
    }

    // 资源回收
    public static void recoveryExecutors(String threadName) {
        ExecutorService executorService = executors.remove(threadName);
        if (executorService != null) {executorService.shutdown();}
    }
}