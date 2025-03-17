public class LambdaThreadTest {
    public static void main(String[] args) {
        Thread lambdaThread = new Thread(() -> {
            System.out.println("使用Lambda创建的Thread对象:"
                    + Thread.currentThread().getName());
        });
        lambdaThread.start();
    }
}

