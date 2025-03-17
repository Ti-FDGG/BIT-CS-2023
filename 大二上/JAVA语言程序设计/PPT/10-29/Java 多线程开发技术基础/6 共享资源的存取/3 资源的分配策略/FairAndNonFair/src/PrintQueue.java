import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class PrintQueue {
	//互斥锁（因为一台打印机一次只能打一个文档)
	private final Lock queueLock;
	public PrintQueue(boolean fairMode) {
		queueLock = new ReentrantLock(fairMode);
	}

	//打印文档
	public void printDocument(int number) {
		queueLock.lock();
		try {
			long duration = (long) (Math.random() * 10000);
			System.out.printf("%s: 打印第 %d 个文档，耗时 %d 秒\n",
					Thread.currentThread().getName(),
					number,
					(duration / 1000));
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
	}
}
