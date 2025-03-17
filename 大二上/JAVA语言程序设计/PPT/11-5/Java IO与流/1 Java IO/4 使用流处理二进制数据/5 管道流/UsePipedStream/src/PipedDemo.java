import java.io.*;

class Producer extends Thread {
    OutputStream os;

    public Producer(OutputStream o) {
        os = o;
    }

    public void run() {
        int count = 1;
        while (count <= 100) {
            try {
                os.write(count);
                os.flush();
                System.out.println("Producer " + count);
                System.out.flush();
                Thread.sleep(100);
                count++;
            } catch (Exception e) {
            }
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    InputStream is;
    public Consumer(InputStream s) {
        is = s;
    }
    public void run() {
        int x = 0, sum = 0;
        do {
            try {
                x = is.read();
                if (x != -1) {
                    System.out.println("Consumer " + x);
                    sum += x;
                }
                System.out.flush();
                //处理时间与发送时间不一致，会导致数据积压或等待
                Thread.sleep(50);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } while (x != -1);
        try {
            System.out.println("收到的数累加和：" + sum);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public class PipedDemo {
    public static void main(String[] args) throws IOException {
        var pis = new PipedInputStream();
        var pos = new PipedOutputStream();
        //创建管道
        pos.connect(pis);
        //启动两个线程，进行通讯
        Producer p = new Producer(pos);
        Consumer c = new Consumer(pis);
        p.start();
        c.start();
    }
}
