public class UserPrinterInterface {
    public int value;

    public static void main(String[] args) {
        Printer printer = msg -> System.out.println(msg);
        //调用函数式接口所定义的默认方法
        printer.welcome();
        //直接调用函数式接口的静态方法
        Printer.staticMethod();
    }
}

@FunctionalInterface
interface Printer {
    void print(String msg);
    //定义在函数式接口中的默认方法
    default void welcome() {
        print("Hello");
    }

    static void staticMethod(){
        System.out.println("static method of Printer");
    }
}
