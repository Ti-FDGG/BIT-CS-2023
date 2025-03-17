public class JoinWithTimeoutNotLeadToDeadLock {
    public static void main(String[] args) throws InterruptedException {
        var mainThread = Thread.currentThread();
        Thread aThread = new Thread(() -> {
            System.out.println("A线程正在运行");
            try {
                System.out.println("A线程等待主线程运行结束,最长等待2秒……");
                mainThread.join(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("A线程运行结束");
        });
        System.out.println("主线程等待A线程运行结束……");
        aThread.start();
        aThread.join();
        System.out.println("程序运行结束");
    }
}

