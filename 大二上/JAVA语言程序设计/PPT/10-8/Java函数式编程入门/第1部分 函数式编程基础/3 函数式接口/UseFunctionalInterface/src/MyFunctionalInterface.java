@FunctionalInterface
public interface MyFunctionalInterface {
    //接口常量
    final int MAX_VALUE = 255;
    //唯一的抽象公有方法
    void func();
    //放置Object类的公有方法
    int hashCode();
    String toString();
    boolean equals(Object obj);

    private void instancePrivateMethod() {
        System.out.println("JDK9新增：接口中的私有实例方法");
    }

    default void defaultMethod() {
        System.out.println("JDK8新增：接口中的默认方法");
        instancePrivateMethod();  //调用接口中的私有实例方法
    }

    private static void staticPrivateMethod() {
        System.out.println("JDK9新增，接口中的私有静态方法");
    }

    static void staticMethod() {
        System.out.println("JDk8新增：接口中的静态方法");
        staticPrivateMethod();//调用接口中的私有静态方法
    }
}

//此类自动拥有接口所定义的默认方法和静态方法
class MyFunctionalClass implements MyFunctionalInterface {
    @Override
    public void func() {
        System.out.println("调用MyFunctionalClass.func()");
    }
}

class UseMyFunctionalInterface {
    public static void main(String[] args) {
        //调用接口的静态方法
        MyFunctionalInterface.staticMethod();
        //访问接口中的常量字段
        System.out.println(MyFunctionalInterface.MAX_VALUE);
        System.out.println("\n=========================\n");
        //实例化一个实现了接口的类
        MyFunctionalInterface obj = new MyFunctionalClass();
        //通过接口变量访问接口中的成员
        obj.func();
        obj.defaultMethod();
        System.out.println(obj.hashCode());
        System.out.println("\n=========================\n");
        //让接口变量接收一个Lambda表达式
        obj=()->System.out.println("将Lambda表达式赋值给接口变量");
        obj.func();
        obj.defaultMethod();
        System.out.println(obj.hashCode());
    }
}
