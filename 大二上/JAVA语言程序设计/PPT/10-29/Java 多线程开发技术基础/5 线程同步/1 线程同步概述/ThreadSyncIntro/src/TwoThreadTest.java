public class TwoThreadTest {
    public static void main(String[] args) {
        new MyThread("线程一").start();
        new MyThread("线程二").start();
    }
}

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println(getName() + "处理" + i);
    }
}
