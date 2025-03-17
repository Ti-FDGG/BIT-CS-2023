import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class TalkingClockTest2 {
    public static void main(String[] args) {
        TalkingClock2 clock = new TalkingClock2();
        clock.start(1000, true);
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock2 {
    public void start(int interval, final boolean beep) {
        //匿名内部类
        var listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Date now = new Date();
                System.out.println("At the tone, the time is " + now);
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        };
        Timer t = new Timer(interval, listener);
        t.start();
    }
}
