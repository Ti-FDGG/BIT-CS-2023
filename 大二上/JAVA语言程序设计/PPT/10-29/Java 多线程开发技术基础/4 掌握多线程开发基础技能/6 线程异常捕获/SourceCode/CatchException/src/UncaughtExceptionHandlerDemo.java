
public class UncaughtExceptionHandlerDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadFunc());
        // 设定线程异常处理程序
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        // 启动线程
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("示例运行结束\n");
    }
}

class ThreadFunc implements Runnable {
    @Override
    public void run() {
        // 以下这句，将抛出异常
        int number = Integer.parseInt("TTT");
        // 这句将永远无法执行到，因为前面有异常抛出
        System.out.printf("Number: %d ", number);
    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.print("捕获到了线程抛出的异常。\n");
        System.out.printf("线程Id: %s\n", t.getId());
        System.out.printf("异常信息: %s: %s\n",
                e.getClass().getName(), e.getMessage());
        System.out.print("异常堆栈: \n");
        e.printStackTrace(System.out);
        System.out.printf("线程状态: %s\n", t.getState());
    }
}
