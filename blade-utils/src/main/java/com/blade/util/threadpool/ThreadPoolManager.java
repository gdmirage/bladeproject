package com.blade.util.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author blade
 * 2019/9/16 16:40
 */
public class ThreadPoolManager {

    private final static int CORE_POOL_SIZE = 5;
    private final static int MAX_POOL_SIZE = 10;
    private final static long KEEP_ALIVE_TIME = 1000;

    private final static BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();

    private final static ThreadFactory THREAD_FACTORY = new NamedThreadFactory("common-thread-pool");

    private static ThreadPoolExecutor threadPoolExecutor;

    public static void executeThread(Runnable runnable) {
        if (WORK_QUEUE.size() % 10 == 0) {
            System.out.println(WORK_QUEUE.size());
        }

        getInstance().execute(runnable);
    }

    public synchronized static ThreadPoolExecutor getInstance() {
        if (null == threadPoolExecutor) {
            synchronized (ThreadPoolManager.class) {
                if (null == threadPoolExecutor) {
                    System.out.println("init common thread pool");
                    threadPoolExecutor = new ThreadPoolExecutor(
                            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, WORK_QUEUE, THREAD_FACTORY);
                }
            }
        }

        return threadPoolExecutor;
    }

    public static void main(String[] args) {
        System.out.println("main start");
        for (int i = 0; i < 10000; i++) {
            executeThread(() -> {
                System.out.println("hahaa");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
