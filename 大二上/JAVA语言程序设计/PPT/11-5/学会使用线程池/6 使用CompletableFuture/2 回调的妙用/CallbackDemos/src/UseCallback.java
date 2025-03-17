import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class UseCallback {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //useThenRun();
        //useWhenComplete();
        //useThenApply();
        useThenAccept();

        //transformResult();
    }

    private static void useThenRun() {
        // 创建异步任务，并返回future
        var taskA = CompletableFuture.runAsync(
                () -> {
                    var threadName=Thread.currentThread().getName();
                    // 返回计算结果
                    System.out.println(threadName+"计算完成，结果为：Hello.");
                });

        // 在future上施加事件，当future计算完成后回调该事件，并返回新future
        var taskB = taskA.thenRun(
                () -> {
                    var threadName=Thread.currentThread().getName();
                    //当oneFuture任务计算完成后做一件事情
                    System.out.println(threadName+"执行计算完成后被回调的方法---");
                });
        //同步等待对应的任务完成
        taskB.join();
    }

    private static void useThenAccept() {
        var future = CompletableFuture
                .supplyAsync(() -> {
                    var threadName = Thread.currentThread().getName();
                    System.out.println(threadName + "生成随机数。");
                    return new Random().nextInt();
                });
        //使用thenAcceptAsync，可接收前一个任务的结果
        future.thenAcceptAsync(result -> {
            var threadName = Thread.currentThread().getName();
            System.out.println(threadName + "执行异步回调函数，得到的随机数为：" + result);
        }).thenRun(() -> {
            var threadName = Thread.currentThread().getName();
            System.out.println(threadName + "执行同步回调函数执行");
        }).join();
    }

    private static void useThenApply()
            throws InterruptedException, ExecutionException {
        // 创建异步任务
        CompletableFuture<String> stepOne = CompletableFuture.supplyAsync(
                () -> "这是第一步的结果");
        // 第一步完成后，继续进行第二步
        CompletableFuture<String> stepTwo = stepOne.thenApply(
                prevResult -> {
                    //返回加工后结果
                    return prevResult + "\n这是第二步的结果";
                });
        // 3.同步等待twoFuture对应的任务完成，并获取结果
        System.out.println(stepTwo.get());
    }

    private static void useWhenComplete() {
        // 创建异步任务，并返回future
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> "这是第一个任务的结果。");
        future.whenComplete((result, throwable) -> {
            // 如果没有异常，打印异步任务结果
            if (null == throwable) {
                System.out.println(result);
            } else {
                //打印异常信息
                System.out.println(throwable.getLocalizedMessage());
            }
        }).join();
    }

    //完成数据转换工作
    private static int doubleValue(int value) {
        return value * 2;
    }

    private static void transformResult() {
        var future = CompletableFuture.supplyAsync(() -> 100);
        //使用thenApply转换数据，使用thenAccept接收最后的处理结果
        future.thenApply(UseCallback::doubleValue)
                .thenAccept(System.out::println);
    }
}