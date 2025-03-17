import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedMethodDemo {
    public static void main(String[] args) {
//        multiThreadInvokeOneSyncMethod();
//        multiThreadInvokeMultiSyncMethod();
//        multiThreadInvokeSyncAndNonSyncMethod();
//        multiThreadInvokeMultiObjSameSyncMethod();
//        multiThreadInvokeMutliSyncStaticMethod();
        multiThreadInvokeSyncStaticAndInstanceMethod();

    }



    //多线程访问同一个实例的同一个同步方法
    private static void multiThreadInvokeOneSyncMethod() {
        var obj = new SynchronizedClass();
        new Thread(obj::syncMethod).start();
        new Thread(obj::syncMethod).start();
    }

    //多线程访问同一个实例的不同同步方法
    private static void multiThreadInvokeMultiSyncMethod() {
        var obj = new SynchronizedClass();
        new Thread(obj::syncMethod1).start();
        new Thread(obj::syncMethod2).start();
    }

    //多线程访问同一个实例的同步与不同步方法
    private static void multiThreadInvokeSyncAndNonSyncMethod() {
        var obj = new SynchronizedClass();
        new Thread(obj::nonSyncMethod).start();
        new Thread(obj::syncMethod).start();
    }

    //多线程访问不同实例的同一个同步方法
    private static void multiThreadInvokeMultiObjSameSyncMethod() {
        var obj1 = new SynchronizedClass();
        new Thread(obj1::syncMethod).start();
        var obj2 = new SynchronizedClass();
        new Thread(obj2::syncMethod).start();
    }

    //多个线程顺序访问不同的同步静态方法
    private static void multiThreadInvokeMutliSyncStaticMethod() {
        new Thread(SynchronizedClass::syncStaticMethod).start();
        new Thread(SynchronizedClass::syncOtherStaticMethod).start();
    }

    //多线程访问同一个类的同步静态与同步实例方法
    private static void multiThreadInvokeSyncStaticAndInstanceMethod() {
        var obj = new SynchronizedClass();
        new Thread(obj::syncMethod).start();
        new Thread(SynchronizedClass::syncStaticMethod).start();

    }
}
