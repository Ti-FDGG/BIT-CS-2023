import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class FixedRateTask implements Runnable {
    private String name;
    public FixedRateTask(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.printf("%s: 运行: %s\n", name, LocalTime.now());
    }
}

public class ScheduledAtFixedRateDemo {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        System.out.printf("Main: 开始于 %s\n", LocalTime.now());
        FixedRateTask task = new FixedRateTask("Task");
        //1秒后，每隔2秒运行任务
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task,
                1, 2, TimeUnit.SECONDS);
        for (int i = 0; i < 10; i++) {
            System.out.printf("Main: 再有 %d 毫秒将会运行下一个定时任务\n",
                    result.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //关闭线程池
        executor.shutdown();
        System.out.printf("Main: 没有更多的任务: %s\n", LocalTime.now());
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: 结束于: %s\n", LocalTime.now());
    }
}
