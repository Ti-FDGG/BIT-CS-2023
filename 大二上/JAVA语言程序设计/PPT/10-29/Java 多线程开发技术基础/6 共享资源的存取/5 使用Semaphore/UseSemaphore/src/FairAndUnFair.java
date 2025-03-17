import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FairAndUnFair {
    //第2个参数值为true或false，会影响到线程的执行顺序
    static Semaphore semaphore = new Semaphore(1,true);

    public static void main(String[] args) {
        Runnable threadFunc=()->{
            var threadName=Thread.currentThread().getName();
            try {
                semaphore.acquire();
                System.out.println(threadName+"开始运行");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                semaphore.release();
                System.out.println(threadName+"结束运行");
            }
        };

        Thread[] threadArray = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threadArray[i] = new Thread(threadFunc);
            threadArray[i].start();
        }

    }
}
