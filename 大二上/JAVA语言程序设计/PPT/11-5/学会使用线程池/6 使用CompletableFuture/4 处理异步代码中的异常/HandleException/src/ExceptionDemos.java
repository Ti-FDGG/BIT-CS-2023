import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ExceptionDemos {
    public static void main(String[] args) {
        //dealWithException();
        //dealWithException2();
        explicitSetException();
    }
    private static void dealWithException() {
        var future = CompletableFuture.supplyAsync(() -> {
            throw new IllegalStateException("故意抛出的异常");
            //return 100;
        });
        try {
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("捕获到异常——" + e.getMessage());
        }
    }

    private static void dealWithException2() {
        var future = CompletableFuture.supplyAsync(() -> {
            throw new IllegalStateException("故意抛出的异常");
        });
        try {
            var result = future.exceptionally(e -> {
                System.out.println("exceptionally()捕获到异常——" + e.getMessage());
                return 100;
            }).get();
            System.out.println("结果为：" + result);
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("捕获到异常——" + e.getMessage());
        }
    }

    private static void explicitSetException(){
        CompletableFuture<String> future=new CompletableFuture<>();
        new Thread(()->{
            future.completeExceptionally(new Exception("显式设置异常"));
        }).start();
        try{
            future.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
