public class VisitMultiSyncMethodDemo {
    public static void main(String[] args) {
        var obj = new MyClass();
        Thread thread1 = new Thread(() -> {
            try {
                obj.f();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                obj.g();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();
    }
}

class MyClass {
    public synchronized void f() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "运行f()");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(500);
            System.out.println(threadName + ":f():" + i);
        }
    }

    public synchronized void g() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "运行g()");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(200);
            System.out.println(threadName + ":g():" + i);
        }
    }
}

