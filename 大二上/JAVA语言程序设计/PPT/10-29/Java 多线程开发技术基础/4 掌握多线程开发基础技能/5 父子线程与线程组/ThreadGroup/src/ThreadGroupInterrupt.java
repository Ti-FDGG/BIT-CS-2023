import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ThreadGroupInterrupt {
    public static void main(String[] args) {
        //创建一个线程组
        var group = new ThreadGroup("TestGroup");
        //启动三个线程，并将其置于同一个线程组中
        Runnable runnable = ThreadGroupInterrupt::task;
        for (int i = 0; i < 3; i++) {
            new Thread(group, runnable).start();
        }
        System.out.println("敲回车键结束所有的线程");
        new Scanner(System.in).nextLine();
        //向线程组发出中断请求
        group.interrupt();
    }

    //线程将要执行的函数
    private static void task() {
        var threadName = "线程" + Thread.currentThread().getName();
        System.out.println(threadName + "开始运行...");
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(threadName + "已退出");
    }
}
