import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TheadLocalTest {
    public static void main(String[] args) {
        int processorCount = Runtime.getRuntime().availableProcessors();
        System.out.println("本机CPU核数：" + processorCount);
        // 创建多个线程
        for (int i = 0; i < 2 * processorCount; i++) {
            Thread thread = new Thread(new ThreadLocalFunc());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }
}

class ThreadLocalFunc implements Runnable {
     // 每个线程，都有自己的startDate实例
    private static ThreadLocal<Date> startDate =
             ThreadLocal.withInitial(() -> new Date());
    @Override
    public void run() {
        System.out.printf("线程开始: %s : %s\n",
                Thread.currentThread().getId(), startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("线程结束: %s : %s\n",
                Thread.currentThread().getId(), startDate.get());
    }
}
