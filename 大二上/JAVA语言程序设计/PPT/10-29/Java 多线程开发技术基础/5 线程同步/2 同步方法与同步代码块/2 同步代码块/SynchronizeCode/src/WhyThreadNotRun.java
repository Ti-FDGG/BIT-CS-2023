public class WhyThreadNotRun {
    static void print(String info) throws InterruptedException {
        synchronized (info) {
            while (true) {
                System.out.println(Thread.currentThread().getName()
                        + " is working...");
                Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) {
        var threadA = new Thread(() -> {
            try {
                print("Hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadA");
        var threadB = new Thread(() -> {
            try {
                print("Hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadB");
        threadA.start();
        threadB.start();
    }


}
