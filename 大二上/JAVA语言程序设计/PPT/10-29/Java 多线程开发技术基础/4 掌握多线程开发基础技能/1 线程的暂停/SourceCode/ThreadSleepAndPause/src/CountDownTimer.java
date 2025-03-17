import java.util.concurrent.TimeUnit;

//一个使用sleep实现的倒计时计数器
public class CountDownTimer {
    private int count;

    public int getCount() {
        return count;
    }

    public CountDownTimer(int seconds) {
        count = seconds;
    }

    void countDown() {
        count--;
    }

    public static void main(String[] args) throws InterruptedException {
        var timer = new CountDownTimer(10);
        int remain = 0;
        while ((remain = timer.getCount()) != 0) {
            System.out.println("剩余" + remain + "秒");
            timer.countDown();
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("时间到！");
    }
}
