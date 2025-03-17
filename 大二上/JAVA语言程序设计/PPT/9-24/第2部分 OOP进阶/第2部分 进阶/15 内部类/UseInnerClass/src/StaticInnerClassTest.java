
public class StaticInnerClassTest {
    public static void main(String[] args) {
        //调用静态内部类的静态方法
        TopClass.NestedClass.visit();
        //实例化静态内部类对象，并调用它的实例方法
        var obj = new TopClass.NestedClass();
        obj.printInfo();
    }
}

class TopClass {
    private static void func() {
        System.out.println("TopClass.func");
    }
    private static int topClassValue = 100;
    //静态内部类
    static class NestedClass {
        public void printInfo() {
            System.out.println("静态内部类的实例方法。");
            //存取外部类的静态字段
            System.out.println("topClassValue = " + topClassValue);
        }

        public static void visit() {
            //调用外部类的静态方法
            func();
            //存取外部类的静态字段
            System.out.println(topClassValue);
        }
    }
}
