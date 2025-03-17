import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierTest {


    public static void main(String[] args) {
        //howToUseCyclicBarrier();

        //reuseCyclicBarrier();

        multiPhase();
    }

    private static void howToUseCyclicBarrier() {
        //指定有两个需要同步的线程，并且传入一个回调函数，当线程全部到达同步点后调用
        CyclicBarrier cb = new CyclicBarrier(2, () -> {
            System.out.println("所有线程都已经成功到达线程同步点");
        });
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                var threadName = Thread.currentThread().getName();
                try {
                    System.out.println(threadName + "开始运行...");
                    //随机休眠一段时间
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(threadName + "到达同步点。");
                    //等待其他线程的到达
                    cb.await();
                    System.out.println(threadName + "运行结束。");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    private static void reuseCyclicBarrier() {
        //每次有3个线程需要同步
        CyclicBarrier cb = new CyclicBarrier(3);
        //但现在有6个线程……
        for (int i = 0; i < 6; i++) {
            Runnable threadFunc = () -> {
                var threadName = Thread.currentThread().getName();
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(threadName + "正在等待其它线程……");
                    //await()方法是可以被重复调用的
                    cb.await();
                    System.out.println(threadName + "重新出发...");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            };
            new Thread(threadFunc).start();
        }
    }

    //展示多线程、多阶段同步
    private static void multiPhase() {
        //代表当前阶段的编号
        AtomicInteger currentPhase = new AtomicInteger(1);
        final CyclicBarrier cb = new CyclicBarrier(3, () -> {
            System.out.println("\n===阶段" + currentPhase.getAndAdd(1) + "完成===\n");
        });  //有3个参与者
        for (int i = 0; i < 3; i++) {
            Runnable runnable = () -> {
                try {
                    var threadName = Thread.currentThread().getName();
                    Thread.sleep((long) (Math.random() * 10000));
                    arriveDestination("线程" + threadName + "到达集合地点1。", cb);
                    cb.await();
                    Thread.sleep((long) (Math.random() * 10000));
                    arriveDestination("线程" + threadName + "到达集合地点2。", cb);
                    cb.await();
                    Thread.sleep((long) (Math.random() * 10000));
                    arriveDestination("线程" + threadName + "到达最后目的地。", cb);
                    cb.await();
                    System.out.println("线程" + Thread.currentThread().getName() + "工作结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            Thread th = new Thread(runnable);
            th.start();
        }
    }

    private static void arriveDestination(String info, CyclicBarrier cb) {
        StringBuilder sb = new StringBuilder();
        //获取当前同步阶段中己经在等待的线程个数
        int waitingNumber = cb.getNumberWaiting();
        sb.append(info);
        switch (waitingNumber) {
            case 0 -> sb.append("我是第一个到的。");
            case 1 -> sb.append("前面已有一人已到达。");
            case 2 -> sb.append("现在人都到齐了。");
        }
        System.out.println(sb.toString());
    }
}
