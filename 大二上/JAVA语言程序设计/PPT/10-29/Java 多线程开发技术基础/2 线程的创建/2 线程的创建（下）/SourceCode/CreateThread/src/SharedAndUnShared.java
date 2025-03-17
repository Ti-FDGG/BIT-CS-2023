public class SharedAndUnShared {
    public static void main(String[] args) {
//        unsharedVariable();
//        sharedObject();
        privateObject();
    }

    private static void privateObject() {
        Runnable threadFunc = () -> {
            MyClass privateObj = new MyClass(0);
            for (int i = 0; i < 5; i++) {
                privateObj.setValue(privateObj.getValue() + 1);
                System.out.println(Thread.currentThread().getName()
                        + "存取私有对象：" + privateObj.getValue());
            }
        };
        new Thread(threadFunc).start();
        new Thread(threadFunc).start();
    }

    private static void sharedObject() {
        //在多个线程外部实例化的对象
        MyClass shareObj = new MyClass(0);
        Runnable threadFunc = () -> {
            for (int i = 0; i < 5; i++) {
                //线程访问外部对象，并修改它的字段璺
                shareObj.setValue(shareObj.getValue() + 1);
                System.out.println(Thread.currentThread().getName() +
                        "存取共享对象：" + shareObj.getValue());
            }
        };
        new Thread(threadFunc).start();
        new Thread(threadFunc).start();
    }

    private static void unsharedVariable() {
        Runnable threadFunc = () -> {
            int localvalue = 0;
            for (int i = 0; i < 5; i++) {
                localvalue++;
                System.out.println(Thread.currentThread().getName()
                        + "存取私有变量：" + localvalue);
            }
        };
        new Thread(threadFunc).start();
        new Thread(threadFunc).start();
    }

}

class MyClass {
    private int value = 0;

    public MyClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
