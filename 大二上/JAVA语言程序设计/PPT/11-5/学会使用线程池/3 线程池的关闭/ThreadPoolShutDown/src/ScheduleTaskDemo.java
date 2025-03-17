import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ScheduledTask implements Callable<String> {
	private String name;
	public ScheduledTask(String name) {
		this.name = name;
	}
	@Override
	public String call() throws Exception {
		System.out.printf("%s: 开始于 : %s\n", name, LocalTime.now());
		return "Hello, world";
	}
}

public class ScheduleTaskDemo {

	public static void main(String[] args) {
		var executor =  Executors.newScheduledThreadPool(1);
		System.out.printf("Main: 开始于 %s\n", LocalTime.now());
		// 执行5个任务
		for (int i = 0; i < 5; i++) {
			ScheduledTask task = new ScheduledTask("Task " + i);
			//实现延迟调用
			executor.schedule(task, i + 1, TimeUnit.SECONDS);
		}
		// 关闭线程池
		executor.shutdown();
		// 等待线程池的中止
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("线程池于: %s 正式关闭。", LocalTime.now());
	}
}
