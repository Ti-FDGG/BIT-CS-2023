public class MustHasLock {
    public static void main(String[] args) throws InterruptedException {
        throwIllegalMonitorStateException();
        blockMain();
    }

    //只有wait没有notify，无限阻塞等待
    private static void blockMain() throws InterruptedException {
        Object lock = new Object();
        System.out.println("临界区之前");
        synchronized (lock) {
            System.out.println("临界区内部，wait()之前");
            lock.wait();
            System.out.println("临界区内部，wait()之后");
        }
        System.out.println("临界区之后");
    }

    private static void throwIllegalMonitorStateException() {
        try {
            Object newString = new Object();
            //当前线程没有锁，以下这句运行时会抛出
            //IllegalMonitorStateException
            newString.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
