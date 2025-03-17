import java.util.Random;

public class TheadSafeObject {
    //内部包容的数据对象
    private final MyData myData = new MyData();
    public MyData getMyData() {
        return myData;
    }
    // 将被多线程同时访问的对象
    private static final ThreadLocal<TheadSafeObject> objectstore
            = new ThreadLocal<>();
    // 构造函数私有，不允许直接new它
    private TheadSafeObject() {
    }
    // 外界只能通过这个方法获取实例
    public static TheadSafeObject getInstance() {
        TheadSafeObject instance = objectstore.get();
        if (instance == null) {
            instance = new TheadSafeObject();
            objectstore.set(instance);
        }
        return instance;
    }
    //当线程结束时，应该注意释放掉内存
    public static void disposeInstance() {
        objectstore.remove();
    }

    @Override
    public String toString() {
        return "TheadSafeObject{" +
                "myData=" + myData +
                '}' + hashCode();
    }

    public static void main(String[] args) {
        // 启动五个线程，运行相同的代码，看看是否能不用加锁而安全访问TheadSafeObject实例
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    String threadname = Thread.currentThread().getName();
                    // 创建一个线程独享的对象
                    TheadSafeObject obj = TheadSafeObject.getInstance();
                    obj.getMyData().increase();
                    System.out.println(threadname + ":" + obj);
                    // 清理资源
                    TheadSafeObject.disposeInstance();
                }
            }).start();
        }
    }

}