import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class AvoidBlock {
    static Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {
        Runnable threadFunc = () -> {
            var threadName = Thread.currentThread().getName();
            if (semaphore.tryAcquire()) {
                System.out.println(threadName + "获取了运行许可。");
                try {
                    for(int i=0;i<5;i++){
                        System.out.print(threadName+"["+i+"],");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    System.out.println();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
            } else {
                System.out.println(threadName + "未获得运行许可。");
            }
            System.out.println(threadName+"退出");
        };
        for (int i = 0; i < 5; i++) {
            new Thread(threadFunc).start();
        }
    }
}
