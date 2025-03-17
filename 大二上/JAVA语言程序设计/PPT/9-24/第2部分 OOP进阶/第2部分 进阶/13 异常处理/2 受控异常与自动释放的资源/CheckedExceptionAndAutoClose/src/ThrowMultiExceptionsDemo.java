import java.io.IOException;
import java.util.Random;

public class ThrowMultiExceptionsDemo {
    public static void main(String[] args) {
        try {
            throwsTest();
        } catch (IOException e) {
            System.out.println("只需要捕获一个异常就可以了");
        }
    }

    //此方法声明会抛出两个异常
    private static void throwsTest()
            throws ArithmeticException, IOException {
        System.out.println("这只是一个测试");
        int ranValue = new Random().nextInt(100);
        if (ranValue % 2 == 0) {
            throw new IOException();
        } else {
            throw new ArithmeticException();
        }
    }
}