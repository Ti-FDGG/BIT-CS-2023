public class JoinTest {
    public static void main(String[] args)
            throws InterruptedException {
        System.out.println("主线程执行");
        Thread otherThread = new Thread(() -> {
            try {
                System.out.println("辅助线程开始..");
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(1000);
                    System.out.println(i + ":辅助线程执行..");
                }
                System.out.println("辅助线程执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        otherThread.start(); //启动线程

        try {
            //辅助线程加入主线程
            otherThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 5; i++) {
            Thread.sleep(500);
            System.out.println(i + ": 主线程正在执行...");
        }

        System.out.println("主线程 执行完毕");
    }
}