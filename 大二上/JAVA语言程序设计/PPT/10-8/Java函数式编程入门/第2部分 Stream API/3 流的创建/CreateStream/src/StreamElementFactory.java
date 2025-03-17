import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamElementFactory {

    public static void main(String[] args) {
//        generateExample();
//        iterateExample();
        useIterateWithPredicate();
    }

    //使用流元素工厂构建流
    private static void generateExample() {
        //整数生产“流水线”，每次调用，生成一个[0,100)区间内的整数
        Supplier<Integer> intFactory = () -> (int) (Math.random() * 100);
        //调用整数元素“工厂”生产出10个“整数” 产品
        Stream<Integer> stream = Stream.generate(intFactory).limit(10);
        //输出所有元素
        stream.forEach(System.out::println);
    }

    //使用迭代法构建流
    private static void iterateExample() {
        //起始值为1
        int seed = 1;
        //使用递推公式：a[n]=a[n-1]+2 生成奇数
        UnaryOperator<Integer> intFactory = n -> n + 2;
        //生成包容10个奇数的流
        Stream<Integer> stream = Stream.iterate(seed, intFactory).limit(10);
        //输出所有元素
        stream.forEach(System.out::println);
    }
    //在使用迭代法时，可以指定约束条件
    //此例生成5的倍数数值流
    private static void useIterateWithPredicate() {
        IntPredicate predicate = n -> n <= 100;
        IntUnaryOperator next = n -> n + 5;
        IntStream.iterate(0, predicate, next)
                .forEach(System.out::println);
    }
}
