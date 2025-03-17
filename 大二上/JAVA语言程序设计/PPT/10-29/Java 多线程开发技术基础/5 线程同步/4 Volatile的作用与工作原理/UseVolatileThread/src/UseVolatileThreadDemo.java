public class UseVolatileThreadDemo extends Thread {
    //不加volatile，将会死循环
    private volatile boolean isRunning = true;
    public boolean isRunning() {
        return isRunning;
    }
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    @Override
    public void run() {
        System.out.println("进入run了");
        while (isRunning == true) {
        }
        System.out.println("线程被停止了！");
    }

    public static void main(String[] args)
            throws InterruptedException {
        UseVolatileThreadDemo thread = new UseVolatileThreadDemo();
        thread.start();
        Thread.sleep(1000);
        thread.setRunning(false);
        System.out.println("已经赋值为false");
    }
}

