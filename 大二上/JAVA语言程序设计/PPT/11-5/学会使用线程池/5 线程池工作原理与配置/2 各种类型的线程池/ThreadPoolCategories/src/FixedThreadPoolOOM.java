import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolOOM {
    private static ExecutorService executorService =
            Executors.newFixedThreadPool(1);
    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new SubThread());
        }
    }
}
class SubThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"正在执行");
            //休眠很长的时间
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
