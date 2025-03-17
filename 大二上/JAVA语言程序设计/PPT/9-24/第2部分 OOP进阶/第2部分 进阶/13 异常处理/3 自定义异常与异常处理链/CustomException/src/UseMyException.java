public class UseMyException {
    public static void main(String[] args) {
        var obj = new MyClass();
        try {
            obj.someMethod();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}

class MyClass {
    //检测某些条件是否满足
    boolean testSomeCondition() {
        //...
        return true;
    }

    void someMethod() throws MyException {
        //当某些条件被满足时,抛出自定义的异常
        if (testSomeCondition() == true) {
            throw new MyException();
        }
        //其它代码
    }
}
