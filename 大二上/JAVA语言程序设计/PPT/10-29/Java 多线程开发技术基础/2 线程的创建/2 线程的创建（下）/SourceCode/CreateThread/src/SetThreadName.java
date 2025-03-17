public class SetThreadName {
    public static void main(String[] args) {
        new MyNamedThread("我的线程").start();
    }
}

class MyNamedThread extends Thread {
    public MyNamedThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + "正在工作。");
    }
}


