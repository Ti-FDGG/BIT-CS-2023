public class RunWhatMethod {
    public static void main(String[] args) {
        Thread th = new Thread(() -> System.out.println("Runnable.Run()")) {
            public void run() {
                //super.run();
                System.out.println("Thread.Run()");
            }
        };
        th.start();
    }
}

