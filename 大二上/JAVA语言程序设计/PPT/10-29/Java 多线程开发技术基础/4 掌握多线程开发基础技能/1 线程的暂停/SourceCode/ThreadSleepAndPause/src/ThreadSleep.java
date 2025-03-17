import java.util.concurrent.TimeUnit;
public class ThreadSleep {
    public static void main(String[] args) {
        pauseThread();
    }

    //让线程暂停的基本方法
    private static void pauseThread() {
        Thread th = new Thread(new Runnable() {
            private int counter = 0;

            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(500);
                        //也可以使用以下方式暂停线程的执行
                        //TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter++;
                    System.out.println(counter);
                }
            }
        });
        th.start();
    }


}
