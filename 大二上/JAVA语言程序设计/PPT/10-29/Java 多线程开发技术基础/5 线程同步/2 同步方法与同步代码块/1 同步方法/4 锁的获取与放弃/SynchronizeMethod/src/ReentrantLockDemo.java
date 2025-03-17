public class ReentrantLockDemo {
    public synchronized void a() {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + "调用同步方法a");
        //继续调用另一个同步方法
        b();
    }

    public synchronized void b() {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + "调用同步方法b");
    }

    public static void main(String[] args) {
        new Thread(() -> {
            new ReentrantLockDemo().a();
        }).start();
    }
}

