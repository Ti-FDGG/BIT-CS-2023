import java.util.concurrent.CompletableFuture;

public class SyncToAsync {
    public static void main(String[] args) {
        syncToAsync().join();
    }
    //模拟一个需要执行较长时间的同步任务
    private static void longTask() throws InterruptedException {
        var currentThread = Thread.currentThread().getName();
        System.out.println(currentThread + "线程正在干活，需要耗费一定的时间");
        Thread.sleep(3000);
        System.out.println(currentThread + "线程的工作已经完成。");
    }

    //封装同步方法为异步调用
    private static CompletableFuture<Void> syncToAsync() {
        return CompletableFuture.runAsync(() -> {
            try {
                longTask(); //同步方法，封装了要执行的工作任务
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
