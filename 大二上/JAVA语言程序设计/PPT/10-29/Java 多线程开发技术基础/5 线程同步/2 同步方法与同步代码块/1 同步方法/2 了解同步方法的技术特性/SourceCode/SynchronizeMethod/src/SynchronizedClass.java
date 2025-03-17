
//此类定义了一些同步方法，主要用于展示同步方法的技术特性
public class SynchronizedClass {
    //非同步方法
    public void nonSyncMethod(){
        new ThreadHelper().process();
    }
    //同步方法
    public synchronized void syncMethod(){
        new ThreadHelper().process();
    }
    //两个指定了名字的同步方法
    public synchronized void syncMethod1(){
        new ThreadHelper("syncMethod1").process();
    }
    public synchronized void syncMethod2(){
        new ThreadHelper("syncMethod2").process();
    }

    //两个同步静态方法
    public synchronized static void syncStaticMethod(){
        new ThreadHelper().process();
    }
    public synchronized static void syncOtherStaticMethod(){
        new ThreadHelper().process();
    }
}
