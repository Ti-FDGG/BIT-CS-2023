import java.util.Random;

class Consumer implements Runnable {
    private final NoSyncStore store;
    public Consumer(NoSyncStore noSyncStore) {
        this.store = noSyncStore;
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


class Producer implements Runnable {
    private final NoSyncStore store;
    public Producer(NoSyncStore noSyncStore) {
        this.store = noSyncStore;
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
            // 将整数保存到仓库中
            store.setProduct(product);
        }
    }
}

public class ProductNoSyncTest {
    public static void main(String[] args) {
        NoSyncStore store = new NoSyncStore();
        // 消费者线程
        new Thread(new Consumer(store)).start();
        // 生产者线程
        new Thread(new Producer(store)).start();
    }
}

class NoSyncStore {
    // -1 表示目前没有产品
    private int product = -1;
    // 这个方法由生产者线程调用
    public void setProduct(int product) {
        this.product = product;
        System.out.printf("生产者存入 (%d)%n", this.product);
    }

    // 这个方法由消费者线程调用
    public int getProduct() {
        int p = this.product;
        System.out.printf("消费者取走 (%d)%n", this.product);
        return p;
    }
}