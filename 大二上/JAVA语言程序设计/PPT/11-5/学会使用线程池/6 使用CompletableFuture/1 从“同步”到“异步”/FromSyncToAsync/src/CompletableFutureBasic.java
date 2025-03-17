import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableFutureBasic {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        runAsync();
//        runAsyncUseOtherThreadPool();
        supplyAsync();
    }

    private static void supplyAsync()
            throws InterruptedException, ExecutionException {
        Supplier<Integer> task = () -> new Random().nextInt();
        var future = CompletableFuture.supplyAsync(task);
        var result = future.get();
        System.out.println(result);
    }

    private static void runAsync() {
        //以异步方式运行一个任务
        var future = CompletableFuture.runAsync(() -> {
            var threadName = Thread.currentThread().getName();
            System.out.println(threadName + "正在工作...");
        });
        //等待任务的完成
        future.join();
    }

    private static void runAsyncUseOtherThreadPool() {
        var threadPool = Executors.newCachedThreadPool();
        //以异步方式运行一个任务
        var future = CompletableFuture.runAsync(() -> {
            var threadName = Thread.currentThread().getName();
            //输出：pool-1-thread-1正在工作...
            System.out.println(threadName + "正在工作...");
        }, threadPool);
        //等待任务的完成
        future.join();
    }
}
