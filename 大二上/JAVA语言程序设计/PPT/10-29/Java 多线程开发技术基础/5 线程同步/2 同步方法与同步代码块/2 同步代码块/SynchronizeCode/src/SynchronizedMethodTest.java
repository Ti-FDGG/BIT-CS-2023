public class SynchronizedMethodTest {
    public static void main(String[] args) {
        SynchroizedClass obj = new SynchroizedClass();
        TestThread1 t1 = new TestThread1(obj);
        TestThread2 t2 = new TestThread2(obj);
        t1.start();
        t2.start();
    }
}

class SynchroizedClass {
    private Object myLock = new Object();

    public void printHello() {
        synchronized (myLock) {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("hello: " + i);
            }
        }

    }

    public void printWorld() {
        synchronized (myLock) {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("world: " + i);
            }
        }


    }
}

class TestThread1 extends Thread {
    private SynchroizedClass example;

    public TestThread1(SynchroizedClass example) {
        this.example = example;
    }

    @Override
    public void run() {
        this.example.printHello();
    }
}

class TestThread2 extends Thread {
    private SynchroizedClass example;

    public TestThread2(SynchroizedClass example) {
        this.example = example;
    }

    @Override
    public void run() {
        this.example.printWorld();
    }
}
