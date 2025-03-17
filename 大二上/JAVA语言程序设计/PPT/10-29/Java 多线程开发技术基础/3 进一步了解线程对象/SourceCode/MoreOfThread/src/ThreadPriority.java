public class ThreadPriority {
    public static void main(String[] args) {
        System.out.println("main的线程优先级："
                + Thread.currentThread().getPriority());
        Thread t1 = new Thread();
        System.out.println("t1的线程优先级：" + t1.getPriority());
        Thread t2 = new Thread(() -> {
            Thread t3 = new Thread();
            System.out.println("t3的线程优先级： "
                    + t3.getPriority());
        });
        t2.setPriority(6);
        t2.start();
        System.out.println("t2的线程优先级：" + t2.getPriority());
    }
}
