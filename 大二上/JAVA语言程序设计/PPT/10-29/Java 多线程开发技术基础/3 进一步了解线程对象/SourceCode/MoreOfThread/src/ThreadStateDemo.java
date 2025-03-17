import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        //线程运行结束标记
        AtomicBoolean finish = new AtomicBoolean(false);
        Thread thread = new Thread(() -> {
            String currentThreadName = Thread.currentThread().getName();
            System.out.println("工作线程:" + currentThreadName + "正在运行");
            System.out.println("工作线程:"+currentThreadName + "开始休眠1秒。");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("工作线程:"+currentThreadName + "结束休眠，之后立即退出");
            //设置线程结束标记
            finish.set(true);
        });

        Thread.State state = thread.getState();
        System.out.println("工作线程状态：" + state);
        thread.start();
        while (!finish.get()) {
            Thread.State currentState = thread.getState();
            if (currentState != state) {
                System.out.println("工作线程状态：" + currentState);
                state = currentState;
            }
        }
        thread.join();
        System.out.println("工作线程状态：" + thread.getState());
    }
}
