package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

//为线程池中的每个线程，提供一个“独享”的工作对象
public class UseThreadPool {
    //用于为Worker实例生成唯一的Id值
    private static final AtomicInteger workerCount = new AtomicInteger();

    public static void main(String[] args) {
        //创建一个包容两个线程的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        //每次get，都会得到一个新的Worker实例
        ThreadLocal<Worker> workers = ThreadLocal.withInitial(() -> {
            return new Worker("Worker" + workerCount.getAndIncrement());
        });
        //创建1000个待处理的任务
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            Runnable runnable = () -> {
                try {
                    //分配一个Worker实例完成特定的工作任务
                    workers.get().process("任务" + finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            //将任务提交给线程池
            threadPool.submit(runnable);
        }
        //关闭线程池
        threadPool.shutdown();
    }
}
