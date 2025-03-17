import java.util.concurrent.TimeUnit;

public class ExceptionAbortLock {
    public synchronized void process1() {
        var threadName = Thread.currentThread().getName();
        //构建一个死循环
        for (int i = 0; ; i++) {
            System.out.println(threadName + "正在处理" + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (i == 3) {
                throw new RuntimeException("抛出异常");
            }
        }
    }

    public synchronized void process2() {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + "正在调用process2()");
    }

    public static void main(String[] args)
            throws InterruptedException {
        var obj = new ExceptionAbortLock();
        new Thread(obj::process1).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(obj::process2).start();
    }
}
