public class StackOverflow {
    public static void main(String[] args) {
        var thread = new Thread(StackOverflow::doSomethingBad);
        thread.start();
    }

    //构造一个无限递归的函数
    static void doSomethingBad() {
        doSomethingBad();
    }
}

