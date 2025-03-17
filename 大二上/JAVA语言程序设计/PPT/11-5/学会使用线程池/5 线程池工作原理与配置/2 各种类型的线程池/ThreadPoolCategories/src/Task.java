import java.time.LocalTime;

//一个用于测试线程池的任务类
public class Task implements Runnable {
    private String _info = "";
    public Task() {
    }
    public Task(String info) {
        _info = info;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var threadName = Thread.currentThread().getName();
        if (_info.length()== 0) {
           _info="执行线程任务";
        }
        System.out.println(threadName + _info +" "+ LocalTime.now());
    }
}