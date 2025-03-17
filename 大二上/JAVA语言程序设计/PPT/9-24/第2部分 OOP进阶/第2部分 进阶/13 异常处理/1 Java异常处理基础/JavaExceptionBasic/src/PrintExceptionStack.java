public class PrintExceptionStack {

    public static void main(String[] args) {
        try {
            method1();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("以下是打印出来的异常堆栈信息……");
            e.printStackTrace();
        }
    }
    public static void method1() throws Exception {
        method2();
    }
    public static void method2() throws Exception {
        method3();
    }
    public static void method3() throws Exception {
        throw new Exception("在method3中抛出了异常");
    }
}

