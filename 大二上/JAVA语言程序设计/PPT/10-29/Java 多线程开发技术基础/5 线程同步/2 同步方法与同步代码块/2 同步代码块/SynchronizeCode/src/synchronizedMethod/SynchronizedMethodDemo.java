package synchronizedMethod;

public class SynchronizedMethodDemo {
    public static void main(String[] args) {
        //multiThreadInvokeOneSyncMethod();

        //multiThreadInvokeMultiSyncMethod();

        //multiThreadInvokeSyncAndNonSyncMethod();

        //multiThreadMultiObjSameSyncMethod();


        multiThreadInvodeMutliSyncStaticMethod();
    }

    //多个线程顺序访问不同的同步静态方法
    private static void multiThreadInvodeMutliSyncStaticMethod() {
        new Thread(MyExampleClass::syncStaticMethod).start();
        new Thread(MyExampleClass::syncOtherStaticMethod).start();
    }

    //多线程访问不同实例的同步实例方法
    private static void multiThreadMultiObjSameSyncMethod() {
        var obj1 = new MyExampleClass();
        var obj2 = new MyExampleClass();
        new Thread(obj1::syncMethod).start();
        new Thread(obj2::syncMethod).start();
    }

    //多线程访问同一个实例的同步与不同步方法
    private static void multiThreadInvokeSyncAndNonSyncMethod() {
        var obj = new MyExampleClass();
        new Thread(obj::nonSyncMethod).start();
        new Thread(obj::syncMethod).start();
    }

    //多线程访问同一个实例的不同同步方法
    private static void multiThreadInvokeMultiSyncMethod() {
        var obj = new MyExampleClass();
        new Thread(obj::syncMethod1).start();
        new Thread(obj::syncMethod2).start();
    }

    //多线程访问同一个实例的同一同步方法
    private static void multiThreadInvokeOneSyncMethod() {
        var obj = new MyExampleClass();
        new Thread(obj::syncMethod).start();
        new Thread(obj::syncMethod).start();
    }

}
