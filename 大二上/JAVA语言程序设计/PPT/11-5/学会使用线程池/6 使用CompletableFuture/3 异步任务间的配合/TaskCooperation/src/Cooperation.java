import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class Cooperation {
    public static void main(String[] args) {
        useAllOfToWait();
        //useAnyOfToWaitFastest();
    }
    private static void useAllOfToWait() {
        var first = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行异步任务一");
            return 1;
        });
        var second = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行异步任务二");
            return 2;
        });
        var third = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行异步任务三");
            return 3;
        });

        var all = CompletableFuture.allOf(first, second, third);
        all.thenRun(() -> {
            try {
                System.out.println("所有异步任务都己经执行完毕了！");
                var finalResult = first.get() + second.get() + third.get();
                System.out.println("结果为：" + finalResult);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void useAnyOfToWaitFastest() {
        Supplier<String> stringSupplier = () -> {
            int ranValue = new Random().nextInt(10000);
            var threadName = Thread.currentThread().getName();
            var info = threadName + "休眠" + ranValue + "毫秒";
            System.out.println(info);
            try {
                Thread.sleep(ranValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return info;
        };
        var first = CompletableFuture.supplyAsync(stringSupplier);
        var second = CompletableFuture.supplyAsync(stringSupplier);
        CompletableFuture.anyOf(first, second).thenAccept(result -> {
            System.out.println("收到最快的线程传出的信息为：" + result);
        }).join();
    }
}
