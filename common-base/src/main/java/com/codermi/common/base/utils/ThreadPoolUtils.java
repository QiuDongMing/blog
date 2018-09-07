package com.codermi.common.base.utils;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiudm
 * @date 2018/9/7 9:53
 * @desc
 */
public class ThreadPoolUtils {

    private static final int corePoolSize = 5;

    private static final int maximumPoolSize = 200;

    private static final Long keepAliveTime = 30L;

    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);

    private static ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger threadNum = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "myThread thread:" + threadNum.getAndIncrement());
        }
    };


    private static ThreadPoolExecutor threadExecutor;

    private ThreadPoolUtils() {

    }

    static {
        if (threadExecutor == null) {
            threadExecutor = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, threadFactory);
        }
    }

    public static void execute(Runnable runnable) {
        threadExecutor.execute(runnable);
    }

    public Future addExecuteTask(Callable callable) {
        return threadExecutor.submit(callable);
    }

}
