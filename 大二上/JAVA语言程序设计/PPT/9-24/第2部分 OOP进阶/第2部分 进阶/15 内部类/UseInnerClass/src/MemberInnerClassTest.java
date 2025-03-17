
public class MemberInnerClassTest {

    public static void main(String[] args) {
        //在实例化外部类时，私有的内部类对象也同时创建
        OuterClass outer = new OuterClass();
        //此方法在内部会调用私有内部类中的方法
        outer.testInnerClass();
        //公有的内部类，是可以直接在外部实例化的
        //注意其独特的new方式
        OuterClass.PublicInnerClass obj=outer.new PublicInnerClass();
        obj.printInfo();
    }
}

class OuterClass {
    private int outerValue = 100;

    private void printValue() {
        System.out.println(outerValue);
    }

    private static void outerStaticMethod(String info) {
        System.out.println(info);
    }

    public void testInnerClass() {
        //外部类可以访问内部类的私有成员
        innerObject.innerClassField++;
        innerObject.innerClassMethod();
        innerObject.visit();
    }

    //在OuterClass类创建实例时，自动实例化一个私有的内部类对象
    private PrivateInnerClass innerObject = new PrivateInnerClass();

    // 私有成员内部类
    private class PrivateInnerClass {
        private int innerClassField = 200;
        public void visit() {
            outerValue = 200;  //访问外部类的私有字段
            printValue();    //调用外部类的私有实例方法
            //调用外部类的私有静态方法
            outerStaticMethod("From InnerClass Object");
        }
        private void innerClassMethod() {
            System.out.println("InnerClass.innerClassMethod");
        }
    }

    //公有
    public class PublicInnerClass{
        public void printInfo(){
            //可以访问外部类的私有成员
            outerValue++;
            System.out.println("OuterClass.PublicInnerClass.printInfo()");
        }
    }

}
