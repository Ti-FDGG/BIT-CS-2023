public class UseCallback {

    public static void main(String[] args)
            throws InterruptedException {
        //一个简单的计算任务，求1到100的和
        Runnable task = () -> {
            long sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            //结果出来之后，调用回调函数显示最终结果
            callback(sum);
        };
        System.out.println("主线程启动工作线程，执行计算任务。");
        var worker = new Thread(task);
        worker.start();
        System.out.println("主线程退出。");
    }

    //供工作线程回调的函数，此函数负责处理工作线程的工作结果
    public static void callback(long result) {
        System.out.println("计算结果为：" + result);
    }
}
