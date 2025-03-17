import java.util.concurrent.locks.LockSupport;

public class ParkAndUnpark {
    public static void main(String[] args) throws InterruptedException {
        //线程一，活干到一半，停下来等通知
        Thread thread1 = new Thread(() -> {
            var threadName = Thread.currentThread().getName();
            for (int i = 0; i < 8; i++) {
                System.out.println(threadName + ",i=" + i);
                if (i == 3) {
                    System.out.println(threadName + "在此暂停，等待通知……");
                    LockSupport.park();
                }
            }
        });
        //线程二，向线程一发送通知：”你可以继续干活了……“
        Thread thread2 = new Thread(() -> {
            var threadName = Thread.currentThread().getName();
            System.out.println(threadName + "正在运行");
            System.out.println(threadName + "发送解锁通知……");
            LockSupport.unpark(thread1);
        });

        //启动线程一
        thread1.start();
        //主线程休眠2秒
        Thread.sleep(2000);
        //启动线程二
        thread2.start();
        //等待两个线程的结束
        thread1.join();
        thread2.join();
    }
}
