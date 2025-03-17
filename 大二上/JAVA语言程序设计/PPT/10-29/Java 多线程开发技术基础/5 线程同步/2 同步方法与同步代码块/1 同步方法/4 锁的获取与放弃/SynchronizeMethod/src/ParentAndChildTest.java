public class ParentAndChildTest {
    public static void main(String[] args) {
        testInvokeParent();
//        testInvokeChild();

//        testPolymorphism();
    }

    //测试多态场景
    private static void testPolymorphism() {
        Parent parent = new Child();
        new Thread(parent::f).start();
        new Thread(parent::f).start();
    }

    //测试多线程调用子类的重写后的同步方法
    private static void testInvokeChild() {
        Child child = new Child();
        new Thread(child::f).start();
        new Thread(child::f).start();
    }

    //测试多线程调用父类的同步方法
    private static void testInvokeParent() {
        Parent parent = new Parent();
        new Thread(parent::f).start();
        new Thread(parent::f).start();
    }
}

class Parent {
    public synchronized void f() {
        new ThreadHelper().process();
    }
}

class Child extends Parent {

    @Override
    public void f() {
        new ThreadHelper().process();
    }
//    @Override
//    public void f() {
//       super.f();
//    }
}
