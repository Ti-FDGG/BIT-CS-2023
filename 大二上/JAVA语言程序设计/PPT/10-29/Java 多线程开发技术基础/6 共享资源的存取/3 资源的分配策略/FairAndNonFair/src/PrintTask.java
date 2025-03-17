//一个虚拟的打印任务，每个任务打印两个文档
public class PrintTask implements Runnable {
    //引用打印机打印任务队列
    private final PrintQueue printQueue;
    public PrintTask(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }
    @Override
    public void run() {
        var threadName = Thread.currentThread().getName();
        System.out.printf("%s: 将要开始打印两份文档\n", threadName);
        printQueue.printDocument(1);
        printQueue.printDocument(2);
        System.out.printf("%s: 文档己经全部打印完毕\n", threadName);
    }
}
