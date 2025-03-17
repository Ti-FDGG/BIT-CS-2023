
public class IntroduceLambda {

    public static void main(String[] args) {

        traditionalCode();

        useLambda();
    }

    private static void traditionalCode() {
        //传统方法，定义一个类实现接口，创建这个类的对象，
        //再把它传给doWithMyInterface()方法
        MyClass obj = new MyClass();
        doWithMyInterface(obj);

        //或者写一个本地内部类，实现这一接口
        class MyInnerClass implements MyInterface{
            @Override
            public void func() {
                System.out.println("本地内部类，实现接口");
            }
        }
        //实例化本地内部类对象，传给示例方法
        doWithMyInterface(new MyInnerClass());

        doWithMyInterface(new MyInterface() {
            @Override
            public void func() {
                System.out.println("使用匿名内部类，实现接口");
            }
        });
    }

    private static void useLambda() {
        //Java 8：定义一个Lambda表达式，将其引用保存到变量中，
        //再把它传给doWithMyInterface()方法
        //从而可以节省下新定义一个类的工作任务
        MyInterface lambdaObj = () -> {
            System.out.println("Explicit Define Lambda object's func()");
        };
        doWithMyInterface(lambdaObj);

        //Java 8：直接把一个Lambda表达式作为doWithMyInterface()方法的参数
        //不仅不需要定义一个单独的类，甚至不再需要定义一个变量
        doWithMyInterface(() -> {
            System.out.println("inline lambda object's func()");
        });

        MyInterface lambdaObj2 = () -> {
            System.out.println("另一个Lambda表达式");
        };
        //Lambda表达式，也可以直接执行
        lambdaObj2.func();
    }

    public static void doWithMyInterface(MyInterface obj) {
        obj.func();
    }

}


interface MyInterface {
    void func();
}

class MyClass implements MyInterface {
    @Override
    public void func() {
        System.out.println("MyClass's func()");
    }
}