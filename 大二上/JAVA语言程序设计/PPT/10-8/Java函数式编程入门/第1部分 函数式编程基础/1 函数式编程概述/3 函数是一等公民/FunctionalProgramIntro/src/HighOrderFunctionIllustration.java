import java.util.function.Function;
import java.util.function.Supplier;

public class HighOrderFunctionIllustration {
    //返回函数（即Lambda表达式）的函数，称为“High Order Function”
    private static Supplier<String> createCombineAndTransform(
            final String a, final String b,
            final Function<String, String> transformer) {
		//注意：这里把一个Lambda表达式return出去了……
        return () -> {
            String aa = a;
            String bb = b;
            if (transformer != null) {
                aa = transformer.apply(a);
                bb = transformer.apply(b);
            }
            return aa + bb;
        };
    }
    public static void main(String[] args) {
        //传入一个方法引用作为参数，又得到一个Supplier函数对象
        Supplier<String> xformOperation = createCombineAndTransform(
                "Hello ", "World", String::toUpperCase);
        //调用Supplier接口所定义的get方法，获取最终结果
        System.out.println(xformOperation.get());
    }
}
