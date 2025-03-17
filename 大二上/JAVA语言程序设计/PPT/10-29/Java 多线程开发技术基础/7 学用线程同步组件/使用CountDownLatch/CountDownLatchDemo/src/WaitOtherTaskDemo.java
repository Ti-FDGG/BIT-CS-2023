import java.beans.PropertyEditorSupport;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitOtherTaskDemo {

    //用于保存工作的结果
    static AtomicInteger result = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        //需要等待前面3个任务的完成
        CountDownLatch prevTasks = new CountDownLatch(3);
        System.out.println("主线程需要等待三个前置任务的完成");
        //启动前置任务的执行
        processTask1(prevTasks);
        processTask2(prevTasks);
        processTask3(prevTasks);

        //主线程在此等待
        prevTasks.await();
        System.out.println("所有前置工作已完成，主线程负责收尾");
        System.out.println("最终的结果为：" + result.get());
    }

    static void processTask1(CountDownLatch latch) {
        Runnable task = () -> {
            System.out.println("正在执行任务一");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //将工作结果保存到全局变量中
            result.addAndGet(1);
            System.out.println("任务一己完成");
            //发出”本任务完成”的通知
            latch.countDown();
        };
        new Thread(task).start();
    }

    static void processTask2(CountDownLatch latch) {
        Runnable task = () -> {
            System.out.println("正在执行任务二");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //将工作结果保存到全局变量中
            result.addAndGet(2);
            System.out.println("任务二已完成");
            //发出”本任务完成”的通知
            latch.countDown();
        };
        new Thread(task).start();
    }

    static void processTask3(CountDownLatch latch) {
        Runnable task = () -> {
            System.out.println("正在执行任务三");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //将工作结果保存到全局变量中
            result.addAndGet(3);
            System.out.println("任务三已完成");
            //发出”本任务完成”的通知
            latch.countDown();
        };
        new Thread(task).start();
    }
}
