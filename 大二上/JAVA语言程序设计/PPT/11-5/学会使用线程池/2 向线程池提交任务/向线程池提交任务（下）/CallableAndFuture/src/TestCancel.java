import java.util.concurrent.*;

public class TestCancel {
    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    static class FetchInfoTask implements Callable<Info> {
        @Override
        public Info call() throws Exception {
            //Thread.sleep是支持被中断的，
            //如果是其它代码，则可以调用Thread.interrupted()
            //看看是否外界发出了中断请求
            try {
                System.out.println("等待3秒出结果...");
                Thread.sleep(3000);
                //当被取消之后，这句是不会运行的
                System.out.println("在Thread.sleep之后运行");
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
            }
            return new Info("计算任务顺利执行结束");
        }
    }


     public void cancelTask() throws InterruptedException {
        Future<Info> f = exec.submit(new FetchInfoTask());
        Info info = null;
        try {
            System.out.println("等待1秒后取消计算任务");
            Thread.sleep(1000);
            //当cancel方法参数为true时，线程会收到中断请求，否则，会自顾自地运行到结束
            boolean cancel = f.cancel(false);
            System.out.println("cancel的结果：" + cancel);
            //调用cancel方法之后，再次尝试调用get，将引发CancellationException
            info = f.get();
        } catch (InterruptedException e) {
            info=new Info("捕获InterruptedException");
        }
        catch (ExecutionException e) {
            info=new Info("捕获ExecutionException");
        }
        catch (CancellationException e) {
            info = new Info("捕获CancellationException");
        }
        exec.shutdown();
        //等待线程池的中止
        exec.awaitTermination(10,TimeUnit.SECONDS);
        System.out.println(info);
    }


    public static void main(String[] args) throws InterruptedException {
        TestCancel cancelDemo = new TestCancel();
        cancelDemo.cancelTask();

    }
}
