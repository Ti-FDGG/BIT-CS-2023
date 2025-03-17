import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class MultiFutures {

    public static void main(String[] args) throws InterruptedException {
        //可以试着减少线程池中的线程数目，会发现结果是分批呈现的
        ExecutorService service = Executors.newFixedThreadPool(5);
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        //由于线程池中有20个任务，所以，这20个任务是并行执行的
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = service.submit(new CallableTask());
            futures.add(future);
        }
        Thread.sleep(5000);
        //休眠5秒后，由于所有并行任务都己经执行完毕，所以，结果是立即出现的。
        for (int i = 0; i < 20; i++) {
            //依次提取出所有任务的结果
            Future<Integer> future = futures.get(i);
            try {
                Integer integer = future.get();
                System.out.println(integer);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    //一个返回随机数的计算任务
    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
