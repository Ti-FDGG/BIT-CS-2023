public class DaemonThread {

    public static void main(String[] args)
            throws InterruptedException {
        //当进程结束时，输出相应信息
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> System.out.println("进程已经结束")));
//        useForgroundThread();
        useDeamonThread();
    }

    //线程函数
    static Runnable runnable = () -> {
        while (true) {
            System.out.println("线程正在运行中...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    private static void useForgroundThread()
            throws InterruptedException {
        new Thread(runnable).start();
        Thread.sleep(4000);
        System.out.println("主线程执行完毕");
    }

    private static void useDeamonThread()
            throws InterruptedException {
        var thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(4000);
        System.out.println("主线程执行完毕");
    }
}
