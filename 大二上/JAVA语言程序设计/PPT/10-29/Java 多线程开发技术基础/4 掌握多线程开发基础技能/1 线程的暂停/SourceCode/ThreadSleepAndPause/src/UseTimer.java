import java.util.Timer;
import java.util.TimerTask;

public class UseTimer {

    public static void main(String[] args) {
        //将要被定时执行的任务，需要派生自TimerTask抽象类，并实现其抽象方法run()
        TimerTask task = new TimerTask() {
            private int counter = 0;

            public void run() {
                counter++;
                System.out.println(Thread.currentThread().getName()
                        + ":" + counter);
            }
        };
        Timer timer = new Timer();
        //过2秒钟后首次运行，以后每隔3秒运行一次
        timer.schedule(task, 2000, 3000);
    }

}

