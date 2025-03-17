import java.util.Random;

public class DefaultValueDemo {
    public static void main(String[] args) {
        MyThreadLocal<String> threadLocal = new MyThreadLocal<>("这是我指定的默认值");
        Runnable runnable = () -> {
            var threadName = Thread.currentThread().getName();
            //先提取默认值
            System.out.println(threadName + ":" + threadLocal.get());
            //再设置自己的值
            threadLocal.set(threadName + "设置值:" + new Random().nextInt());
            //再次提取值
            System.out.println(threadName + ":" + threadLocal.get());
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}

//一个支持自定义初始值的ThreadLocal类
class MyThreadLocal<T> extends ThreadLocal<T> {
    private final T initValue;

    public MyThreadLocal(T initValue) {
        this.initValue = initValue;
    }

    @Override
    protected T initialValue() {
        return initValue;
    }
}
