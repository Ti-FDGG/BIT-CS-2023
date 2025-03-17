import java.util.concurrent.*;

public class TestTimeout {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    static class FetchInfoTask implements Callable<Info> {
        @Override
        public Info call() throws Exception {
            try {
                System.out.println("等待3秒出结果...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
            }
            return new Info("计算任务顺利执行结束");
        }
    }

    public void printInfo() throws InterruptedException {
        Future<Info> f = threadPool.submit(new FetchInfoTask());
        Info info;
        try {
            //最长等待2秒
            info = f.get(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            info = new Info("捕获InterruptedException");
        } catch (ExecutionException e) {
            info = new Info("捕获ExecutionException");
        } catch (TimeoutException e) {
            info = new Info("捕获TimeoutException");
        }
       //关闭线程池
        threadPool.shutdown();
        //等待线程池的中止
        threadPool.awaitTermination(10,TimeUnit.SECONDS);
        //输出结果
        System.out.println(info);
    }


    public static void main(String[] args)
            throws InterruptedException {
        TestTimeout timeout = new TestTimeout();
        timeout.printInfo();
    }
}
