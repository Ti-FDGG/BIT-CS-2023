public class HoldsLockTest {
    public static void main(String[] args) {
        HoldsLockTest obj = new HoldsLockTest();
        obj.testObjectLock();
    }

    public void testObjectLock() {
        boolean hasLock = false;
        hasLock = Thread.holdsLock(this);
        System.out.println("当前线程执行有锁？" + hasLock);
        synchronized (this) {
            hasLock = Thread.holdsLock(this);
            System.out.println("当前线程执行有锁？" + hasLock);
        }
        hasLock = Thread.holdsLock(this);
        System.out.println("当前线程执行有锁？" + hasLock);
    }
}
