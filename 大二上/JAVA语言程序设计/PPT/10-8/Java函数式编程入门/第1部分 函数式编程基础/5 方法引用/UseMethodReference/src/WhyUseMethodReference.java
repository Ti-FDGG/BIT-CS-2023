import java.util.List;

public class WhyUseMethodReference {
    public static void main(String[] args) {
        useLambdaToPrint();
        useMethodReferenceToPrint();
    }

    private static void useLambdaToPrint() {
        //创建一个包容5个数字的集合
        var nums = List.of(1, 2, 3, 4, 5);
        //使用Lambda遍历输出集合中的所有数字
        nums.forEach(num -> System.out.println(num));
    }

    private static void useMethodReferenceToPrint() {
        var nums = List.of(1, 2, 3, 4, 5);
        //使用方法引用遍历输出集合中的所有数字
        nums.forEach(System.out::println);
    }

}
