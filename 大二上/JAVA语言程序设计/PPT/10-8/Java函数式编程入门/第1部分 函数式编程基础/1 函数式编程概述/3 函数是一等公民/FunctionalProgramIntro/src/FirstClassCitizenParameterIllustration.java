import java.util.function.Function;


public class FirstClassCitizenParameterIllustration {
    //一个接收一个函数的静态方法
    public static String concatAndTransform(String a, String b,
                                            Function<String, String> stringTransform) {
        if (stringTransform != null) {
            a = stringTransform.apply(a);
            b = stringTransform.apply(b);
        }
        return a + b;
    }

    public static void main(String[] args) {
        //可以使用Lambda表达式直接作为上述静态方法的参数
        System.out.println(concatAndTransform("Hello ", "World", (s) -> s.toUpperCase()));
        System.out.println(concatAndTransform("Hello ", "World", (s) -> s.toLowerCase()));
    }

}
