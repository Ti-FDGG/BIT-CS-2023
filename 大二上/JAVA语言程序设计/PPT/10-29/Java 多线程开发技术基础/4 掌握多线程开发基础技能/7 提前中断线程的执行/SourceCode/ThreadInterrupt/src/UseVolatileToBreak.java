import java.util.Scanner;

public class UseVolatileToBreak {
    public static void main(String[] args) {
        var thread = new MyThread();
        thread.start();
        System.out.println("敲回车键退出...");
        new Scanner(System.in).nextLine();
        thread.quit();
    }
}

class MyThread extends Thread {
    private volatile boolean closeFlag = false;
    //private boolean closeFlag = false;
    public void quit() {
        closeFlag = true;
    }

    @Override
    public void run() {
        var threadName = Thread.currentThread().getName();
        System.out.println("线程" + threadName + "正在工作");
        while (!closeFlag) {
            System.out.print(".");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("线程" + threadName + "已经退出。");
    }
}
