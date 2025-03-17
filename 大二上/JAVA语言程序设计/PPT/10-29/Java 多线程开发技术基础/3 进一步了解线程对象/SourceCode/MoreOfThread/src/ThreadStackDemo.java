public class ThreadStackDemo {
    public void first() {
        second();
    }

    public void second() {
        third();
    }

    public void third() {
        printStackTrace();
    }

    public void printStackTrace() {
        //获取当前线程的堆栈信息
        var array = Thread.currentThread().getStackTrace();
        for (StackTraceElement elem : array) {
            System.out.println("className:" + elem.getClassName() + "\n"
                    + "methodName:" + elem.getMethodName() + "\n"
                    + "fileName:" + elem.getFileName() + "\n"
                    + "lineNumber:" + elem.getLineNumber() + "\n");
        }
    }

    public static void main(String[] args) {
        new ThreadStackDemo().first();
    }

}

