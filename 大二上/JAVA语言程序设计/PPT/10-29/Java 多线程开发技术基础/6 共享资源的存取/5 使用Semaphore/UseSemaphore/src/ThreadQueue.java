import java.util.concurrent.Semaphore;

public class ThreadQueue {
    static Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {
        Runnable threadFunc = () -> {
            try {
                semaphore.acquire();
                System.out.printf("当前有%d个线程在等待……\n", semaphore.getQueueLength());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "退出。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        };
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(threadFunc);
            threads[i].start();
        }
    }
}
