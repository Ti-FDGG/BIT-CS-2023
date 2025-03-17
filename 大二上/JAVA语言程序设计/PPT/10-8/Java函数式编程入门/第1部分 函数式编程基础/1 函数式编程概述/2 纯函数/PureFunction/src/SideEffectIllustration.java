
public class SideEffectIllustration {

    // 没有副作用的方法
    public int f1(int x) {
        return x * 2;
    }

    private int state = 0;

    // 有副作用的方法
    public int f2(int x) {
        state++;
        return x * 2 + state;
    }

    public static void main(String[] args) {
        SideEffectIllustration obj = new SideEffectIllustration();
        //创建10个线程，每个线程都调用f1或f2方法，观察多线程环境下
        //Pure Function的输出与有副作用的方法的输出有何区别
        Thread[] theads = new Thread[10];

        for (int i = 0; i < theads.length; i++) {
            final int index = i;
            theads[i] = new Thread(() -> {
                // Note：切换以下两句的注释，观察输出的结果
                //System.out.println(String.format("第%d次,结果为：%d", index + 1, obj.f1(5)));
                System.out.println(String.format("第%d次,结果为：%d", index + 1, obj.f2(5)));
            });
            theads[i].start();
        }
    }


}


