import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class UseInvokeAny {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        //创建3个任务，加入到集合中
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tasks.add(UseInvokeAny::task);
        }
        try {
            //提交给线程池执行，只要有一个运行结束，此方法就返回
            var result = executor.invokeAny(tasks);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();
        System.out.println("程序退出");
    }

    //将要执行的任务，执行时间是随机的
    static String task() {
        var threadName = Thread.currentThread().getName();
        int ranValue = new Random().nextInt(1000, 5000);
        for (int i = 0; i < 4; i++) {
            System.out.println(threadName + "正在处理" + i);
            try {
                Thread.sleep(ranValue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return threadName + "处理结束";

    }
}
