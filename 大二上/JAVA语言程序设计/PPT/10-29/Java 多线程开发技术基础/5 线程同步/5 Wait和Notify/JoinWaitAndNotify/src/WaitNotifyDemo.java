public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        WaitThread t1 = new WaitThread(lock);
        t1.start();
        Thread.sleep(3000);
        NotifyThread t2 = new NotifyThread(lock);
        t2.start();
    }
}

class WaitThread extends Thread {
    private final Object lock;
    public WaitThread(Object lock) {
        super();
        this.lock = lock;
    }
    @Override
    public void run() {
            synchronized (lock) {
                System.out.println("开始 wait time=" + System.currentTimeMillis());
                try {
                    lock.wait(); //阻塞等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结束 wait time=" + System.currentTimeMillis());
            }
    }
}

class NotifyThread extends Thread {
    private final Object lock;
    public NotifyThread(Object lock) {
        super();
        this.lock = lock;
    }
    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("开始 notify time=" + System.currentTimeMillis());
            lock.notify();
            System.out.println("结束 notify time=" + System.currentTimeMillis());
        }
    }
}
