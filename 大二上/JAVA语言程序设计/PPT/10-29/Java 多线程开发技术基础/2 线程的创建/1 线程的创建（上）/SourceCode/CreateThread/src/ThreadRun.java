public class ThreadRun extends Thread {
	public void run() {
		System.out.println("辅助线程名：" + Thread.currentThread().getName());
		for (int i = 0; i < 10; i++)
			System.out.println("Thread counter=" + i);
	}

	public static void main(String[] args) {
		System.out.println("主线程名：" + Thread.currentThread().getName());
		ThreadRun th = new ThreadRun();
		th.start();
		//线程只能调用一次start()，调用多次，会抛出IllegalThreadStateException
		//th.start();
	}
}

