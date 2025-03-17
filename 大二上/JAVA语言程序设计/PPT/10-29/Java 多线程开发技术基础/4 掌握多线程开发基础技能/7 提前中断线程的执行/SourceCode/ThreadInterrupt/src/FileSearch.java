import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 此类在一个文件夹中查找指定名字的文件
 */
public class FileSearch implements Runnable {

	//需要搜索文件的文件夹
	private final String initPath;
	//要寻找的文件
	private final String fileName;

	public FileSearch(String initPath, String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				//在文件夹中查找文件
				directoryProcess(file);
			} catch (InterruptedException e) {
				System.out.printf("%s: 搜索任务己被取消。",
						Thread.currentThread().getName());
			}
		}
	}

	private void directoryProcess(File file)
			throws InterruptedException {
		// 获取此文件夹中的所有文件与子文件夹
		File[] list = file.listFiles();
		if (list != null) {
			for (File value : list) {
				if (value.isDirectory()) {
					System.out.println("搜索文件夹：" + value);
					// 如果是文件夹，则递归搜索它
					directoryProcess(value);
				} else {
					// 如果是文件，看它是不是要找的文件
					fileProcess(value);
				}
			}
		}
		// 如果外界发出了“取消搜索”的请求，则主动抛出InterruptedException
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

	//处理文件
	private void fileProcess(File file) throws InterruptedException {
		// 比对文件名
		if (file.getName().equals(fileName)) {
			System.out.printf("%s : %s\n",
					Thread.currentThread().getName(),
					file.getAbsolutePath());
		}
		// 如果外界发出了“取消搜索”的请求，则主动抛出InterruptedException
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

	public static void main(String[] args) {
		// 在C:盘上使用多线程查找”autoexec.bat“文件
		FileSearch searcher = new FileSearch("C:\\",
				"autoexec.bat");
		Thread thread = new Thread(searcher);
		thread.start();
		// 等待10秒
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("等了10秒，还没有找到，取消搜索任务");
		//中断线程
		thread.interrupt();
	}

}
