public class UseSynchronized {

    public static void main(String[] args) {
        String info1="aaaaaaaaaa";
        String info2="bbbbbbbbbb";
        //=================================
        //测试Printer类的实例方法（同步与不同步均测一下）
        Printer obj=new Printer();
        Thread th1=new Thread(new ThreadFunc(info1,obj));
        Thread th2=new Thread(new ThreadFunc(info2,obj));
        th1.start();
        th2.start();
        //=================================
//		//即使Printer类的实例方法已是同步方法，以下代码运行正常吗？为什么？
//		Printer obj1=new Printer();
//		Printer obj2=new Printer();
//		Thread th1=new Thread(new ThreadFunc(info1,obj1));
//		Thread th2=new Thread(new ThreadFunc(info2,obj2));
//		th1.start();
//		th2.start();
        //=================================
        //测试Printer类的静态方法（同步与不同步均测一下）
//		Thread th1=new Thread(new ThreadFunc2(info1));
//		Thread th2=new Thread(new ThreadFunc2(info2));
//		th1.start();
//		th2.start();
        //=================================
        //如果“混用”Printer类的实例与静态方法，怎样实现同步？
//		Printer obj=new Printer();
//		Thread th1=new Thread(new ThreadFunc(info1,obj));
//		Thread th2=new Thread(new ThreadFunc2(info2));
//		th1.start();
//		th2.start();
    }

}
/**
 * 使用实例方法
 * @author JinXuLiang
 *
 */
class ThreadFunc implements Runnable{

    public ThreadFunc(String info,Printer printer){
        _info=info;
        _printer=printer;
    }
    private String _info;
    private Printer _printer;
    public void run() {

        _printer.instancePrint(_info);
    }

}
/**
 * 使用静态方法
 * @author JinXuLiang
 *
 */
class ThreadFunc2 implements Runnable{

    public ThreadFunc2(String info){
        _info=info;
    }
    private String _info;
    public void run() {

        Printer.staticPrint(_info);
    }

}

/**
 * 用于输出指定的信息
 * @author JinXuLiang
 *
 */
class Printer{
    /**
     * 有两种方法实现同步，试着切换取消相应的注释，运行示例程序查看代码
     * 1 使用synchronized同步块
     * 2 直接使用synchronized修饰方法
     * @param info
     */
    //public synchronized void instancePrint(String info){
    public void instancePrint(String info){
        //	synchronized(this){
        //synchronized(Printer.class){
        for(int i=0;i<info.length();i++)
            System.out.print(info.charAt(i));
        System.out.println();
        //}
    }
    //public synchronized static void staticPrint(String info){
    public  static void staticPrint(String info){
        for(int i=0;i<info.length();i++)
            System.out.print(info.charAt(i));
        System.out.println();
    }
}
