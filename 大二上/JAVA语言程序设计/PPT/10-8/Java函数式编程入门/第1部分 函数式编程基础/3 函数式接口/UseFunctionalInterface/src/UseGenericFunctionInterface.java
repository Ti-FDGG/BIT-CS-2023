import java.util.function.Function;

public class UseGenericFunctionInterface {
    public static void main(String[] args) {
        useJDKFunctionInterface();

        //useMyGenericFunctionInterface();
    }

    private static void useMyGenericFunctionInterface() {
        TriFunction<Integer, Integer, Integer, Integer> add3Number =
                (x, y, z) -> x + y + z;
        //输出：6
        System.out.println(add3Number.apply(1,2,3));
    }

    private static void useJDKFunctionInterface() {
        //接收的Lambda表达式满足以下要求
        //(1)接收一个String类型的字符串参数
        //(2)返回一个Integer类型的结果
        Function<String, Integer> stringLength = str -> str.length();
        //通过apply()方法调用
        System.out.println(stringLength.apply("Hello")); // 5
    }
}
