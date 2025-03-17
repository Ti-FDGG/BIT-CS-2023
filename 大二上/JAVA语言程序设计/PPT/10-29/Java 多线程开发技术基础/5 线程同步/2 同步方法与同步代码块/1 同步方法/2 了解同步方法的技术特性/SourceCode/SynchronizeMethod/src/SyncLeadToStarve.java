import java.util.concurrent.TimeUnit;

public class SyncLeadToStarve {
    public static void main(String[] args)
            throws InterruptedException {
        var obj = new MySyncClass();
        new Thread(obj::process1).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(obj::process2).start();
    }
}

class MySyncClass {
    //太自私了，占有锁就不再放手了~~~
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
        }
    }
    //我需要拥有锁才能运行
    public synchronized void process2() {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + "正在调用process2()");
    }
}
