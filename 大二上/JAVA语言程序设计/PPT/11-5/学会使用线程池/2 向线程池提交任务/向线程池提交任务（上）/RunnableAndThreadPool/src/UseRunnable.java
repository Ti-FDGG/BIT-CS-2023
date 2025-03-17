import java.util.concurrent.Executors;

public class UseRunnable {
    public static void main(String[] args) {
        useThreadPool();
    }
    private static void useThreadPool() {
        //创建一个包容两个线程的线程池
        var executor = Executors.newFixedThreadPool(2);
        //输出：java.util.concurrent.ThreadPoolExecutor
        System.out.println(executor.getClass().getName());

        try {
            for(int i=0;i<50;i++){
                //如果向线程池提交多个任务，可以看到线程对象得到了“重用”
                executor.execute(() -> {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //pool-1-thread-1或pool-1-thread-2
                    System.out.println(Thread.currentThread().getName());
                });
            }

        } finally {
            //线程池启动之后，会一直运行，如果不显式关闭它，会导致本进程一直无法结束。
            executor.shutdown();
        }
    }
}

