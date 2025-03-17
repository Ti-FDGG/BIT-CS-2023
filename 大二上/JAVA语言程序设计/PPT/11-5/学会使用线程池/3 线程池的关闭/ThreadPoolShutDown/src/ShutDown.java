import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        //创建一个包容5个线程的线程池，向它提交10个任务
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            threadPool.execute(new ShutDownTask("任务" + i));
        }
        //等待1.5秒后关闭线程池
        Thread.sleep(1500);
        threadPool.shutdown();
        //检查线程池状态
        System.out.println("isShutdown=" + threadPool.isShutdown());
        System.out.println("isTerminated=" + threadPool.isTerminated());
        try {
            //关闭之后，再次提交一个新任务
            threadPool.execute(new ShutDownTask("新任务"));
        } catch (RejectedExecutionException ex) {
            System.out.println("\n线程池正在关闭中，拒绝了新任务。\n");
        }
    }
}

//一个将要被线程池执行的任务
class ShutDownTask implements Runnable {
    private String taskInfo = "";
    public ShutDownTask(String info) {
        taskInfo = info;
    }
    @Override
    public void run() {
        var threadName = Thread.currentThread().getName();
        try {
            Thread.sleep(1000);
            System.out.println(threadName + "处理：" + taskInfo);
        } catch (InterruptedException e) {
            System.out.println(threadName + "被中断了");
        }
    }
}
