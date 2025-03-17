import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ExplicitSetValue {
    public static void main(String[] args) throws ExecutionException,
            InterruptedException {
        //创建线程池
        var threadPool = Executors.newFixedThreadPool(2);
        //创建一个CompletableFuture对象，使用它来承载计算结果
        CompletableFuture<String> future = new CompletableFuture<>();
        //向线程池提交计算任务，并设置结果
        threadPool.execute(() -> {
            longTask(future);
        });
        System.out.println("---主线程等待计算结果---");
        //get方法会阻塞主线程，直到计算结果，得到了结果
        var result=future.get();
        System.out.println("---主线程得到了结果---");
        System.out.println(result);
        //关闭线程池
        threadPool.shutdown();
    }
    private static void longTask(CompletableFuture<String> future) {
        //休眠3s，模拟任务计算
        try {
            var threadName=Thread.currentThread().getName();
            System.out.println("线程池中的"+threadName+"正在处理数据");
            Thread.sleep(3000);
            //生成一个随机数作为结果
            int result = new Random(System.currentTimeMillis()).nextInt();
            System.out.println("----" +threadName  + "得到了最终处理结果 ----");
            //显式设置结果
            future.complete("这是最终结果：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

