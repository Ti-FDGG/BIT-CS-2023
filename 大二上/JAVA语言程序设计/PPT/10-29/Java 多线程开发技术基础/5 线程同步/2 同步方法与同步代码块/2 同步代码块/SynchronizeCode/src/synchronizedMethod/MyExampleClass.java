package synchronizedMethod;

public class MyExampleClass {
    //非同步方法
    public void nonSyncMethod(){
        new MyTestClass().process();
    }

    public synchronized void syncMethod(){
        new MyTestClass().process();
    }
    public synchronized void syncMethod1(){
        new MyTestClass("syncMethod1").process();
    }
    public synchronized void syncMethod2(){
        new MyTestClass("syncMethod2").process();
    }


    public synchronized static void syncStaticMethod(){
        new MyTestClass().process();
    }
    public synchronized static void syncOtherStaticMethod(){
        new MyTestClass().process();
    }
}
