import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WhatIsLock {
    //Lock是一个接口
    private final Lock lock = new ReentrantLock();
    //将被多线程执行的线程函数
    public void threadFunc()  {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "处理 " + (i + 1));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("===============");
        lock.unlock();
    }

    public static void main(String[] args) {
        var obj = new WhatIsLock();
        for (int i = 1; i <= 4; i++) {
            //谁抢到锁，谁输出。
            new Thread(obj::threadFunc).start();
        }
    }
}
