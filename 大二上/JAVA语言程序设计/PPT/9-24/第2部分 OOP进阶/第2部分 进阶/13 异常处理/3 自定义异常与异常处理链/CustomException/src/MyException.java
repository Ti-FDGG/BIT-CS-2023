//自定义异常类，通常需要重写基类的构造方法
public class MyException extends Exception {
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}

