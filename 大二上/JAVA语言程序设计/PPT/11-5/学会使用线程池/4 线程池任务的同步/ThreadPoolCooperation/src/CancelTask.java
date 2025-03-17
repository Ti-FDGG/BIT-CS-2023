import java.util.concurrent.*;

public class CancelTask {
    public static void main(String[] args) {
        var executor = Executors.newFixedThreadPool(2);
        //var task = new CancelableTask();
        var task = new CancelableTask2();
        System.out.println("主线程提交任务给线程池");
        var result = executor.submit(task);
        //主线程休眠两秒，让线程池执行一些计算任务
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程发出取消任务的请求");
        result.cancel(true);
        // 检查Future对象的状态
        System.out.printf("Main: Cancelled: %s\n", result.isCancelled());
        System.out.printf("Main: Done: %s\n", result.isDone());
        // 关闭线程池
        executor.shutdown();
        System.out.println("主线程退出");
    }
}

//一个可以被取消的任务
class CancelableTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        while (true) {
            System.out.println("任务正在执行...");
            //JDK中的Thread.sleep方法，是支持“提前中断休眠”的
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class CancelableTask2 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.print("任务正在执行");
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("响应外部取消请求，跳出循环");
                break;
            }
        }
        return "任务结束";
    }
}
