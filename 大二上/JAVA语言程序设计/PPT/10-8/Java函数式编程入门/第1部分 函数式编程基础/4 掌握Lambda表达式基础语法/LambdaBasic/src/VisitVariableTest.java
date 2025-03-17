
public class VisitVariableTest {
    private final int counter = 100;

    public static void main(String[] args) {
        VisitVariableTest obj = new VisitVariableTest();
        obj.test("hello");
    }

    private void test(String info) {
        String methodMsg = "test方法中定义的methodMsg变量";
        Printer printer = localMsg -> {
            System.out.println("Lambda可以访问自己定义的局部变量：msg=" + localMsg);
            System.out.println("Lambda还可以访问它所在方法所定义的局部变量：methodMsg=" + methodMsg);
            System.out.println("Lambda也能访问类的实例变量：counter=" + counter);
			System.out.println("Lambda还可以访问方法的参数：info="+info);
        };
        printer.print("Hello,World.");
    }
}

@FunctionalInterface
interface Printer {
    void print(String msg);
}