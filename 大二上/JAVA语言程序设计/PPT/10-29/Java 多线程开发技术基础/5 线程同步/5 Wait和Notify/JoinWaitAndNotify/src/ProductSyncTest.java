import java.util.Random;

class ProducerSync implements Runnable {
    private final SyncStore store;
    public ProducerSync(SyncStore syncStore) {
        this.store = syncStore;
    }
    public void run() {
        System.out.println("生产者开始生产整数......");
        // 生产1到5的整数
        for (int product = 1; product <= 5; product++) {
            try {
                // 暂停随机时间
                Thread.sleep(new Random().nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 将产品保存到仓库中
            store.setProduct(product);
        }
    }
}

class ConsumerSync implements Runnable {
    private final SyncStore store;
    public ConsumerSync(SyncStore syncStore) {
        this.store = syncStore;
    }
    public void run() {
        System.out.println("消费者开始消耗整数......");
        // 消耗5个整数
        for (int i = 1; i <= 5; i++) {
            try {
                // 等待随机时间
                Thread.sleep(new Random().nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 从仓库中取走整数
            store.getProduct();
        }
    }
}

public class ProductSyncTest {
    public static void main(String[] args) {
        SyncStore store = new SyncStore();
        // 生产者线程
        new Thread(new ProducerSync(store)).start();
        // 消费者线程
        new Thread(new ConsumerSync(store)).start();
    }
}

class SyncStore {
    // -1 表示目前没有产品
    private int product = -1;

    // 这个方法由生产者线程调用
    public synchronized void setProduct(int product) {
        if (this.product != -1) {
            try {
                // 目前仓库没有空间收产品，请稍候！
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.product = product;
        System.out.printf("生产者设定 (%d)%n", this.product);
        // 通知等待区中的一个消费者可以继续工作了
        notify();
    }

    // 这个方法由消费者调用
    public synchronized int getProduct() {
        if (this.product == -1) {
            try {
                // 缺货了，请稍候！
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int p = this.product;
        System.out.printf(
                "消费者取走 (%d)%n", this.product);
        this.product = -1; // 取走产品，-1表示目前仓库手上无产品
        // 通知等待区中的一个生产者可以继续工作了
        notify();
        return p;
    }
}