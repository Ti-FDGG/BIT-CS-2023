import java.util.concurrent.*;

public class UseFutureTask {
    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(task);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(integerFutureTask);
        try {
            System.out.println("task运行结果：" + integerFutureTask.get());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

//一个简单的计算任务
class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程正在计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}
