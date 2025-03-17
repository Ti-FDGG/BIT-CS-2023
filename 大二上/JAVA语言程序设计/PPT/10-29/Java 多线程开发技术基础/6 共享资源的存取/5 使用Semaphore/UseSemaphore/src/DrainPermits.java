import java.util.concurrent.Semaphore;

public class DrainPermits {
    public static void main(String[] args) throws InterruptedException {
        var semaphore = new Semaphore(10);
        semaphore.acquire();
        semaphore.acquire();
        System.out.println("剩余许可证：" + semaphore.availablePermits());
        int result = semaphore.drainPermits();
        System.out.printf("一口气申请了%d个许可证，剩余%d个许可证。%n",
                result, semaphore.availablePermits());
    }
}
