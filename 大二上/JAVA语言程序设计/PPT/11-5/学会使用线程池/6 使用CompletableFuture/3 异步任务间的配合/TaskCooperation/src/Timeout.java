import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Timeout {
    public static void main(String[] args) {
        //timeOutDemo();
        timeOutDemo2();
    }
    private static void timeOutDemo() {
        var future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finished";
        });
        try {
            //最长等1秒钟，超时后抛出TimeoutException
            var result = future.orTimeout(1, TimeUnit.SECONDS)
                    .get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void timeOutDemo2() {
        var future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finished";
        });
        try {
            //最长等1秒钟，超时后给一个默认值
            var result = future.completeOnTimeout("超时之后的默认值",
                    1, TimeUnit.SECONDS).get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
