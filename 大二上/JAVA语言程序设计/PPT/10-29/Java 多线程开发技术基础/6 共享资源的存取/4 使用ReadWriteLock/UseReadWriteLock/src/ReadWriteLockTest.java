import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) {
        final var dataSource = new ReadWriteSharedData();
        //3个读线程
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
               while(true) {
                    dataSource.get();
                   try {
                       Thread.sleep((long) (Math.random() * 500));
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }).start();
        }
        //1个写线程
        new Thread(() -> {
            for (int j = 0; j < 5; j++) {
                try {
                    dataSource.put(new Random().nextInt(10000));
                    Thread.sleep((long) (Math.random() * 5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("写入结束！");
            System.exit(0);

        },"写入者线程").start();
    }
}

class ReadWriteSharedData {
    //共享的数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
    private Integer data = null;
    //用于为每个读线程提供一个本地副本
    ThreadLocal<Integer> readData=new ThreadLocal<>();
    //读写锁
    ReadWriteLock rwl = new ReentrantReadWriteLock();

    //读操作
    public void get() {
        //获取读锁
        rwl.readLock().lock();
        try {
            if(!Objects.equals(data, readData.get())){
                System.out.println(Thread.currentThread().getName() + " 读到了新数据 :" + data);
                readData.set(data);
            }
        }
        finally {
            rwl.readLock().unlock();//释放读锁
        }
    }

    //写操作
    public void put(Integer data) {
        //获取写锁
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 准备好了写数据!");
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " 写入了数据: " + data);
        }  finally {
            rwl.writeLock().unlock();//释放写锁
        }
    }

}
