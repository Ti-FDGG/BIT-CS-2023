public class ThreadGroupCatchException {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("myGroup") {
                    // 继承ThreadGroup并重新定义uncaughtException方法
                    // 在线程成员抛出unchecked exception时会执行此方法
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println(t.getName() + ": " + e.getMessage());
                    }
                };
        Thread thread = new Thread(threadGroup, () -> {
            // 抛出unchecked异常
            throw new RuntimeException("用于测试异常捕获而有意抛出的异常");
        });
        thread.start();
    }
}


