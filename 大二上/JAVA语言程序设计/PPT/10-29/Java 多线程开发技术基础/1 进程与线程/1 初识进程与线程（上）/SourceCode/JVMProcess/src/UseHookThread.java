import java.util.concurrent.TimeUnit;

public class UseHookThread {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            var threadName = "HookThread:" + Thread.currentThread().getName();
            System.out.println(threadName + "正在运行...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(threadName + "退出");
        };
        System.out.println("主程序开始执行...");
        Runtime.getRuntime().addShutdownHook(new Thread(runnable));
        System.out.println("主程序退出。");
    }
}
