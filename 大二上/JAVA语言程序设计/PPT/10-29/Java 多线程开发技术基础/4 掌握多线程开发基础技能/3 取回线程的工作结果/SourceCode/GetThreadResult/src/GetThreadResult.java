import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class GetThreadResult {
    public static void main(String[] args)
            throws ExecutionException, InterruptedException {

        //定义一个将被工作线程执行的，有返回值的任务
        Callable<String> task = () -> {
            var begin = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            var elapsed = System.currentTimeMillis() - begin;
            return "工作结束,耗时" + elapsed + "毫秒";
        };

        //Thread不能直接接收一个Callable对象，因此，需要再包一层
        var job = new FutureTask<>(task);
        //创建线程并启动执行
        new Thread(job).start();
        //FutureTask所定义的get()方法用于取回结果，它会在此”阻塞等待“……
        System.out.println("\nresult:" + job.get());
    }
}
