public class UseInheritableThreadLocal {
    private static final InheritableThreadLocal<String> threadLocal =
            new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("这是父线程设置的值");
        var thread = new Thread(() -> {
            System.out.println("子线程读到：" + threadLocal.get());
            threadLocal.set("子线程设置的值");
            System.out.println("子线程读到：" + threadLocal.get());
        });
        thread.start();
        thread.join();
        System.out.println("父线程读到：" + threadLocal.get());
    }
}


