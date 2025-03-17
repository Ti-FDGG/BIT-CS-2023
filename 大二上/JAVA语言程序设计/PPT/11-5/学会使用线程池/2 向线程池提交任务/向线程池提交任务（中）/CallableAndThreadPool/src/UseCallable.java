import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class UseCallable {
    public static void main(String[] args)
            throws InterruptedException {
        //创建线程池
        var es = Executors.newFixedThreadPool(2);
        //提交异步任务
        var future = es.submit(new MyCallableClass());
        //取回结果
        try {
            System.out.println("主线程阻塞等待结果...");
            int value = future.get();
            System.out.println("主线程得到结果：" + value);
            es.shutdownNow();
        } catch (ExecutionException e) {
            System.out.println("捕获到异常：" + e.getMessage());
        }
        System.out.println("程序结束");
    }
}

//使用Callable封装可以返回结果的多线程代码
class MyCallableClass implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        int ranValue = random.nextInt(100);
        int sleepTime = random.nextInt(3000);
        Thread.sleep(sleepTime);
        var threadId = Thread.currentThread().getId();
        System.out.println("线程" + threadId + "返回：" + ranValue);
        return ranValue;
    }
}