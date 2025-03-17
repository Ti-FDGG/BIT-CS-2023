import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.function.*;


public class FunctionInterfaceExample {
    public static void main(String[] args) {
        useFunctionInterface();

        useBiFunctionInterface();

        useUnaryOperator();

        useBinaryOperator();


    }

    private static void useBiFunctionInterface() {
        //检查某字符串是否长度超限
        BiFunction<String, Integer, Boolean> exceedsMaxLength =
                (s, maxLength) -> {
                    int actualLength = s.length();
                    return actualLength > maxLength;
                };

        Scanner scanner = new Scanner(System.in);
        System.out.println("输入一个最多8个字符的字符串");
        String userInput = scanner.nextLine();
        //检查数据是否有效
        boolean result = exceedsMaxLength.apply(userInput, 8);
        if (!result) {
            System.out.println("符合要求");
        } else {
            System.out.println("无效输入");
        }
    }

    private static void useFunctionInterface() {
        //将一个英文句子按空格分割，然后统计得到的单词的个数
        Function<String, Integer> wordCount =
                s -> s.split(" ").length;
        String str = "this is a test.";
        System.out.println(wordCount.apply(str));//4
    }

    private static void useUnaryOperator() {
        //接收一个参数，返回同类型的单个结果
        UnaryOperator<Integer> autoIncrease = num -> ++num;
        System.out.println(autoIncrease.apply(100));
    }

    private static void useBinaryOperator() {
        //接收两个参数，返回单个结果，三个都是同类型的
        BinaryOperator<Integer> add = (x, y) -> x + y;
        var sum = add.apply(100, 200);
        System.out.println(sum);//300
        //BinaryOperator定义了两个静态方法，可以用于求两个数值的最大值或最小值
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(comparator);
        System.out.println(maxBy.apply(100, 200));//200
    }


    private static void IntToDoubleDemo(){
        //接收一个int,返回一个doble
        IntToDoubleFunction func = num -> num * 0.5;
        double result = func.applyAsDouble(100);
        System.out.println(result);//50.0
    }
}
