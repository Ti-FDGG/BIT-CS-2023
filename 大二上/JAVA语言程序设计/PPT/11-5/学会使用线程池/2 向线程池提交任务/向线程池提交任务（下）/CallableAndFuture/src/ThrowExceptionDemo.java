import java.util.concurrent.*;

public class ThrowExceptionDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future<Integer> future = service.submit(new CallableTask());
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            //不管有没有异常抛出，只要线程结束了工作，isDone属性都是true
            System.out.println(future.isDone());
            //虽然在线程池执行计算任务时异常己经抛出，但一定要等到get时才能捕获
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException异常");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException异常");
        }
    }


    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
