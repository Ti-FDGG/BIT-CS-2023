import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {
    //共享资源
    static SharedResource sharedResource = new SharedResource();
    //用于统计消费者总共读取的数据条数
    static AtomicInteger readCount = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        readCount.set(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("敲回车创建生产者线程");
        scanner.nextLine();

        //创建8个生产者线程，每个线程生产5个字符串，一共生产40个字符串
        for (int i = 0; i < 8; i++) {
            Thread writeThread = new Thread(() -> {
                var threadName = Thread.currentThread().getName();
                try {
                    for (int j = 0; j < 5; j++)
                        sharedResource.addString(threadName + "写入数据" + j);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "生产者" + i);
            writeThread.setDaemon(true);
            writeThread.start();
        }

        System.out.println("敲回车创建消费者线程");
        scanner.nextLine();
        //5个消费者
        for (int i = 0; i < 5; i++) {
            Thread readThread = new Thread(() -> {
                var threadName = Thread.currentThread().getName();
                try {
                    //只要还有数据，就不断地消费
                    while (true) {
                        sharedResource.removeString();
                        //统计己消费的数据数量
                        readCount.addAndGet(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "消费者" + i);
            readThread.setDaemon(true);
            readThread.start();
        }
        scanner.nextLine();

        System.out.println("主线程退出。");
        System.out.println("一共消费了" + readCount.get() + "条数据");
    }
}

class SharedResource {
    //5个生产者
    private Semaphore writerSemaphore = new Semaphore(5);
    //3个消费者
    private Semaphore readerSemapnore = new Semaphore(3);
    //用于实现互斥读写
    private ReentrantLock lock = new ReentrantLock();
    //同步生产者线程的写入操作
    private Condition addCondition = lock.newCondition();
    //同步消费者线程的读取操作
    private Condition removeCondition = lock.newCondition();

    //最多可以放4个字符串
    String[] store = new String[4];

    //只要有一个位置为null,就表示可以写入数据
    private boolean isEmpty() {
        for (String s : store) {
            if (s != null) {
                return false;
            }
        }
        return true;
    }

    //全部位置都不为null,表示没有位置可以写入
    private boolean isFull() {
        for (String s : store) {
            if (s == null) {
                return false;
            }
        }
        return true;
    }

    //供生产者线程调用，写入数据
    public void addString(String str) throws InterruptedException {
        try {
            writerSemaphore.acquire();//尝试获取写数据许可
            lock.lock();//定义临界区，实现互斥访问
            while (isFull()) {
                //仓库满，没有位置可写，等待“有空位“的通知
                addCondition.await();
            }
            //有空位了，找到空出来的位置，写入数据
            for (int i = 0; i < store.length; i++) {
                if (store[i] == null) {
                    store[i] = str;
                    System.out.println(str + " 已放入第" + i + "个位置");
                    break;
                }
            }
            removeCondition.signalAll();  //通知读线程：“你们可以读了……”
        } finally {
            lock.unlock();
            writerSemaphore.release();
        }
    }

    //供消费者线程调用，读取之后清除数据
    public void removeString() throws InterruptedException {
        try {
            readerSemapnore.acquire();//申请获得读许可
            lock.lock();//定义临界区，实现互斥访问
            while (isEmpty()) {
                //如果没有数据，则等待"有货可取"的通知
                removeCondition.await();
            }
            //找到有数据的地方，读出来，再将其置为null，现在生产者线程可以在此位置写入
            for (int i = 0; i < store.length; i++) {
                if (store[i] != null) {
                    System.out.println(Thread.currentThread().getName() +
                            "移出第" + i + "个位置的字符串：" + store[i]);
                    store[i] = null;
                    break;
                }
            }
            //通知生产者线程：”东西我拿走了，现在你可以写了……“
            addCondition.signalAll();
        } finally {
            lock.unlock();
            readerSemapnore.release();
        }
    }
}
