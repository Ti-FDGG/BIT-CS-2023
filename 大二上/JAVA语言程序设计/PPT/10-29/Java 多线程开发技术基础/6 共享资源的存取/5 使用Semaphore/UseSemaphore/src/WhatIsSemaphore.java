import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class WhatIsSemaphore {

    //有两个运行许可证
    static Semaphore semaphore = new Semaphore(2);
    //用于生成随机的休眠时间
    static Random random = new Random();

    public static void main(String[] args) {

        Runnable threadFunc = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                System.out.printf("%s申请获取运行许可证,当前可用数目：%d\n", threadName,
                        semaphore.availablePermits());
                semaphore.acquire();
                int sleepTime = random.nextInt(10) + 1;
                System.out.printf("%s获得了运行许可证，运行需时%d秒,当前剩余许可证数：%d\n",
                        threadName,
                        sleepTime,
                        semaphore.availablePermits());
                TimeUnit.SECONDS.sleep(sleepTime);
                semaphore.release();
                System.out.printf("\n%s结束运行，归还运行许可证。\n\n", threadName);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        System.out.println("运行许可证数目：2，启动线程：5");
        Thread[] threads = new Thread[5];
        for (int i = 1; i <= threads.length; i++) {
            var thread = new Thread(threadFunc);
            thread.setName("工作线程-" + i);
            threads[i - 1] = thread;
            thread.start();
        }
        //等待所有线程运行结束
        Arrays.stream(threads).forEach(th -> {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("所有线程均已结束。");
    }
}
