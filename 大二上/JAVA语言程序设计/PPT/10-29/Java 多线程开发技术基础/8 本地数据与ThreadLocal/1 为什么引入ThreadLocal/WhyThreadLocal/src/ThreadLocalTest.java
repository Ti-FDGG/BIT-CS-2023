import java.util.Random;

public class ThreadLocalTest {
	//放在外部的ThreadLocal
	static ThreadLocal<Integer> tl = new ThreadLocal<>();
	public static void main(String[] args) {
		//创建两个线程
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				String threadName = Thread.currentThread().getName();
				int dataValue = new Random().nextInt();
				System.out.println(threadName + "产生了数据：" + dataValue);
				tl.set(dataValue);
				//线程内部new一个对象，通过它来访问数据
				new MyClass().visitData();
			}).start();
		}
	}
	private static class MyClass {
		public void visitData() {
			String threadName = Thread.currentThread().getName();
			Integer data = tl.get();
			System.out.println(threadName + ":" + data);
		}
	}
}
