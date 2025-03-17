import java.util.function.Function;

public class FunctionalInterface {
    public static void main(String[] args) {
        //定义一个函数式接口变量，接收一个Lambda表达式
        Function<Integer, Integer> doubleValue = value -> value * 2;
        //执行Lambda表达式，输出：200
        System.out.println(doubleValue.apply(100));
    }
}
