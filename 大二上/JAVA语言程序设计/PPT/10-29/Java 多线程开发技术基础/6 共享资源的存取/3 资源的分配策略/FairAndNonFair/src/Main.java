import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String args[]) {

        System.out.println("使用线程分配策略： fair-mode = true\n");
        testPrintQueue(true);

//        System.out.println("使用线程分配策略：fair-mode = false\n");
//        testPrintQueue(false);

    }

    private static void testPrintQueue(boolean fairMode) {
        PrintQueue printQueue = new PrintQueue(fairMode);
        // 创建10个线程打印文档
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            var printThread = new Thread(new PrintTask(printQueue), "文档打印者" + i);
            threads[i] = printThread;
            printThread.start();
            //休眠一段时间
            sleepSometime();
        }
        // 等待10个打印线程全部结束
        waitAllThreadToFinish(threads);
    }

    private static void sleepSometime() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void waitAllThreadToFinish(Thread[] threads) {
        Arrays.stream(threads).forEach(thread->{
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
