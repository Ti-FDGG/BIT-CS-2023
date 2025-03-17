public class ThrowDemo {
    public static void main(String[] args) {
//        intDivide1();
        intDivide2();
//        doubleDivide();
    }

    static void intDivide1() {

            int i = 1, j = 0, k;
            k = i / j;

    }

    static void intDivide2() {
        try {
            int i = 1, j = 0, k;
            k = i / j;
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }

    static void doubleDivide() {
        double d1 = 100, d2 = 0, result;
        result = d1 / d2;
        System.out.println("浮点数除以零：" + result);
    }
}
 