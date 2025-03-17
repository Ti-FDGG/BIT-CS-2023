public class RunMutliThread {
    public static void main(String[] args) {
        //启动5个线程
        for (int i = 0; i < 5; i++)
            createDownloadTask();
    }
    //启动一个线程，模拟从网络上下载
    private static void createDownloadTask() {
        Thread downloadThread = new Thread(() -> {
            var threadName = Thread.currentThread().getName();
            System.out.println(threadName + ":正在下载...");
            try {
                Thread.sleep(5000);//暂停5秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + ":下载结束");
        });
        downloadThread.start();
    }
}

