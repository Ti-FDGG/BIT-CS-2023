import java.util.concurrent.CompletableFuture;

public class UseComposeAndCombine {
    public static void main(String[] args) {
        //useComposeFunc();
        useCombineAsyncFunc();
    }



    private static CompletableFuture<String> asyncTask1() {
        return CompletableFuture.supplyAsync(() -> "第一步的结果");
    }

    private static CompletableFuture<String> asyncTask2(String prevResult) {
        return CompletableFuture.supplyAsync(() -> {
            return "收到：" + prevResult + ",继续处理，得到第二步的结果";
        });
    }

    private static void useComposeFunc() {
        //先执行第一个异步任务，接着是第二个，它接收第一个异步任务的结果，最后，第二个结果被输出
        asyncTask1()
                .thenCompose(UseComposeAndCombine::asyncTask2)
                .thenAccept(System.out::println).join();
    }

    private static void useCombineAsyncFunc() {
        var first = CompletableFuture.supplyAsync(() -> "100")
                .thenApply(Integer::parseInt);
        var second = CompletableFuture.supplyAsync(Math::random);
        //异步计算两个函数，然后以这两个函数的返回值为参数，进行计算，得到最终结果，再输出
        first.thenCombine(second, (firstResult, secondResult) -> {
            return firstResult * secondResult;
        }).thenAccept(System.out::println);
    }

}
