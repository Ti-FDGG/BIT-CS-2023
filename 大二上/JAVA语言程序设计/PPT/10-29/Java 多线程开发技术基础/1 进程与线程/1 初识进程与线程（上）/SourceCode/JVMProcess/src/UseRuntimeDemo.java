public class UseRuntimeDemo {
    public static void main(String[] args) {
        System.out.println("JVM版本信息：" + Runtime.version());
        //获取当前虚拟机运行时引用
        var runtime = Runtime.getRuntime();
        int CPU = runtime.availableProcessors();
        System.out.println("本机包容CPU核数：" + CPU);

        //当前虚拟机内存总量
        long totalMemory = runtime.totalMemory();
        System.out.println("totalMemory:" + totalMemory / (1024 * 1024) + "M");
        //当前虚拟机可用最大内存数
        long maxMemory = runtime.maxMemory();
        System.out.println("maxMemory:" + maxMemory / (1024 * 1024) + "M");
        //当前虚拟机可用内存数
        long freeMemory = runtime.freeMemory();
        System.out.println("freeMemory:" +freeMemory / (1024 * 1024) + "M");
    }
}

