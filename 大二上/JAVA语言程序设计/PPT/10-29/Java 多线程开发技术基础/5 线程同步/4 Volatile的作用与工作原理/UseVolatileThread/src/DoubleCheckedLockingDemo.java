public class DoubleCheckedLockingDemo {
    public static void main(String[] args) {
        var obj1 = SingletonUseDCL.getInstance();
        var obj2 = SingletonUseDCL.getInstance();
        //输出：true
        System.out.println(obj1 == obj2);
    }

}


class SingletonUseDCL {
    private static volatile SingletonUseDCL instance;
    private SingletonUseDCL() {
    }
    public static SingletonUseDCL getInstance() {
        if (null == instance) {
            synchronized (SingletonUseDCL.class) {
                if (null == instance) {
                    instance = new SingletonUseDCL();
                }
            }
        }
        return instance;
    }
}

