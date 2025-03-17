import java.util.Scanner;
import java.util.concurrent.Executors;

public class NotTerminatedThreadPool {
    public static void main(String[] args) {
        var threadPool = Executors.newFixedThreadPool(1);
        threadPool.submit(() -> {
            System.out.println("提交一个任务给线程池运行。");
        });
        System.out.println("敲回车键结束程序……");
        new Scanner(System.in).nextLine();
        System.out.println("主线程退出。");
    }
}

