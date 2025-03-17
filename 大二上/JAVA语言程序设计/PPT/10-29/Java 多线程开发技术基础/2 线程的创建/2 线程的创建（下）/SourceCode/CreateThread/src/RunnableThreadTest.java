public class RunnableThreadTest {
    public static void main(String[] args) {
        var threadName = Thread.currentThread().getName();
        System.out.println("主线程名：" + threadName);
        //创建新线程并运行它
        Thread th = new Thread(new MyThread());
        th.start();
        System.out.println("主线程" + threadName + "运行结束");
    }
}

//使用MyThread类封装线程要执行的代码
class MyThread implements Runnable {
    public void run() {
        var threadName = Thread.currentThread().getName();
        System.out.println("辅助线程" + threadName + "已经启动运行");
        for (int i = 0; i < 10; i++)
            System.out.println("Thread counter=" + i);
        System.out.println("线程" + threadName + "运行结束");
    }
}