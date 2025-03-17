import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class TalkingClockTest {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();
        //显示一个对话框，“阻止”主线程立即退出
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

//一个可以定时输出当前时间的时钟类
class TalkingClock {

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
       //实例化一个TimerPrinter内部实例
        ActionListener listener = new TimePrinter();
        //以指定的时间间隔，调用listener对象事先约定好的方法
        Timer t = new Timer(interval, listener);
        t.start();
    }

    private final int interval; //信息打印间隔
    private final boolean beep; //是否鸣叫

    //内部类，完成时间打印任务
    private class TimePrinter implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Date now = new Date();
            System.out.println("At the tone, the time is " + now);
            if (beep) Toolkit.getDefaultToolkit().beep();
        }
    }

}
