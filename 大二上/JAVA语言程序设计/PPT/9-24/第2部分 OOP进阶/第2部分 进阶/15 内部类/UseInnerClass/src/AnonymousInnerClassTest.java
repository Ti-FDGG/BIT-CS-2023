
public class AnonymousInnerClassTest {

    public static void main(String[] args) {

        useTraditionalClass();

        useAnonymouseInnerClass();

    }

    //经典的编程模式
    private static void useTraditionalClass() {
        var obj = new AnonymousInnerClassTest();
        var interfaceImplObj = new MyInterfaceImple();
        obj.useMyInterfaceObject(interfaceImplObj);
    }

    //使用匿名内部类的演示代码
    private static void useAnonymouseInnerClass() {
        var obj = new AnonymousInnerClassTest();
        //使用匿名内部类语法创建MyInterface接口对象
        obj.useMyInterfaceObject(new MyInterface() {
            @Override
            public void func() {
                System.out.println("匿名内部类的方法执行");
            }
        });
        //匿名内部类访问外部类的字段
        obj.InnerVisitOuterField();
    }



    public void useMyInterfaceObject(MyInterface obj) {
        obj.func();
    }

    private int outerField = 100;
    public void InnerVisitOuterField() {
        var innnerObj = new MyInterface() {
            @Override
            public void func() {
                //访问外部类的私有字段
                AnonymousInnerClassTest.this.outerField++;
                System.out.println(outerField);
            }
        };
        innnerObj.func();
    }
}

interface MyInterface {
    void func();
}

class MyInterfaceImple implements MyInterface {
    @Override
    public void func() {
        System.out.println("实现了MyInterface接口的类运行");
    }
}


