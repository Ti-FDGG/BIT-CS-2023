import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//直观展示CachedThreadPool的工作原理
public class HowCachedThreadPoolWork {
    public static void main(String[] args) {
        Server server = new Server();
        //创建1000个任务，提交给线程池执行
        for (int i = 0; i < 1000; i++) {
            ThreadTask task = new ThreadTask("Task " + i);
            server.executeTask(task);
        }
        server.endServer();
    }
}

//内部封装了一个线程池，负责执行计算任务，并输出线程池工作的状态信息
class Server {
    private final ThreadPoolExecutor executor =
            (ThreadPoolExecutor) Executors.newCachedThreadPool();
    public void executeTask(ThreadTask task) {
        System.out.printf("Server: 来了一个新任务\n");
        //执行计算任务
        executor.execute(task);
        System.out.printf("Server:线程池大小: %d\n", executor.getPoolSize());
        System.out.printf("Server:激活线程数: %d\n", executor.getActiveCount());
        System.out.printf("Server:已完成任务: %d\n", executor.getCompletedTaskCount());
    }
    public void endServer() {
        executor.shutdown();
    }
}

class ThreadTask implements Runnable {
    private Date initDate;//创建时间
    private String name;
    public ThreadTask(String name) {
        initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        var threadName = Thread.currentThread().getName();
        System.out.printf("%s: 执行的任务 %s: 创建于: %s\n", threadName, name, initDate);
        System.out.printf("%s: 执行的任务 %s: 开始于: %s\n", threadName, name, new Date());
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: 执行任务 %s: 花费时间 %d 秒\n", threadName, name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: 执行的任务 %s: 结束于: %s\n", threadName, name, new Date());
    }

}
