import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryDemo {
    public static void main(String[] args) {
        var factory = new MyThreadFactory("自定义线程工厂");
        var scanner = new Scanner(System.in);
        String userInput = "";
        System.out.println("敲回车创建新线程，q退出");
        do {
            factory.newThread(new Runnable() {
                @Override
                public void run() {
                    long threadId = Thread.currentThread().getId();
                    System.out.println("线程" + threadId + "执行。");
                }
            }).start();
            userInput = scanner.nextLine();
        } while (!Objects.equals(userInput, "q"));
        System.out.println("演示结束");
    }
}

class MyThreadFactory implements ThreadFactory {
    private int counter;
    private final String name;
    public MyThreadFactory(String name) {
        counter = 0;
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-线程-" + counter);
        counter++;
        System.out.printf("名字为“%s”的 线程%d 创建于 %s\n",
                t.getName(), t.getId(), new Date());
        return t;
    }
}
