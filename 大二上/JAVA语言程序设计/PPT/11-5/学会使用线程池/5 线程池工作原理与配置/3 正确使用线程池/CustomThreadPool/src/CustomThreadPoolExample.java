import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExample {

    public static void doSomethingA() {
        var threadName = Thread.currentThread().getName();
        System.out.println("---" + threadName + " 工作任务A开始---");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---" + threadName + " 工作任务A结束---");
    }

    public static void doSomethingB() {
        var threadName = Thread.currentThread().getName();
        System.out.println("---" + threadName + " 工作任务B开始---");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---" + threadName + " 工作任务B结束---");

    }

    //获取本机CPU核心数
    private final static int AVALIABLE_PROCESSORS =
            Runtime.getRuntime().availableProcessors();
    //自定义线程池
    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
                    AVALIABLE_PROCESSORS * 2, 1,
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(5),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException {
        System.out.println("线程池内置线程个数：" + POOL_EXECUTOR.getCorePoolSize());
        long start = System.currentTimeMillis();
        // 异步执行任务A和B
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //关闭线程池，这时，会等待线程完成当前的任务
        POOL_EXECUTOR.shutdown();
        System.out.println("等待任务执行完毕...");
        // 3.同步等待线程A运行结束
        POOL_EXECUTOR.awaitTermination(10,TimeUnit.SECONDS);
        var workingTime = System.currentTimeMillis() - start;
        System.out.println("运行结束，耗时：" + workingTime + "毫秒");
    }
}
