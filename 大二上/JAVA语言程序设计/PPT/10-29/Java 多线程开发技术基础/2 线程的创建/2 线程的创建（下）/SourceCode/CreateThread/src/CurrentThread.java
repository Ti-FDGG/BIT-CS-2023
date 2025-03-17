public class CurrentThread {
    public static void main(String[] args) {
        //输出：main
        showCurrentThread();
        //输出：Thread-0
        new Thread(CurrentThread::showCurrentThread).start();
    }

    static void showCurrentThread() {
        System.out.println(Thread.currentThread().getName());
    }
}

