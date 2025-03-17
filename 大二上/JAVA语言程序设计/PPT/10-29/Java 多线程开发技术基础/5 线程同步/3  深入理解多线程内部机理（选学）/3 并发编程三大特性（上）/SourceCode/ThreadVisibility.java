public class ThreadVisibility {
    static int data = 0;
    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(50);
                System.out.println(data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //这里对data的修改，子线程是可见的
        data = 1;
        thread.start();
        Thread.sleep(50);
        //这里对data的修改，子线程可能可以看得见，
        //也可能看不见，造成结果的不确定性
        data = 2;
    }
}


