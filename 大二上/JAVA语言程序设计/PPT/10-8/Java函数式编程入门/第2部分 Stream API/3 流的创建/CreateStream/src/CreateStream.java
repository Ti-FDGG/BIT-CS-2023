import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {

        createForCollection();

        createUseStreamOf();

        createUseStreamBuilder();

        createNumberStream();

        createEmptyStream();

    }

    // 基于集合对象创建流
    private static void createForCollection() {
        //创建一个集合对象
        Set<String> names = new HashSet<>();
        names.add("Tom");
        names.add("Jacket");
        // 基于集合对象创建流
        Stream<String> sequentialStream = names.stream();
        //基于集合对象创建并行流（ parallel stream）
        Stream<String> parallelStream = names.parallelStream();
        //基于数组创建流
        int[] numbers = {2, 3, 5, 7, 11, 13};
        var arrayStream = Arrays.stream(numbers);
    }

    //使用Stream.of方法创建流
    private static void createUseStreamOf() {
        //使用可变参数创建流
        var strStream = Stream.of("Java", "Kotlin", "C#");
        //基于数组创建流
        String[] names = {"Ken", "Jeff", "Chris", "Ellen"};
        Stream<String> nameStream = Stream.of(names);
    }

    //使用StreamBuilder创建流
    private static void createUseStreamBuilder() {
        Stream<String> stream = Stream.<String>builder()
                .add("Ken")
                .add("Jeff")
                .add("Chris")
                .add("Ellen")
                .build();
    }

    //创建原始数值类型构成的流
    private static void createNumberStream() {
        //使用可变参数构建数值流
        IntStream stream = IntStream.of(1, 1, 2, 3, 5);
        //使用int数组构建流
        int[] values = {1, 2, 3, 4};
        IntStream stream2 = Arrays.stream(values);

        //生成的流包容 [0,100) 区间的所有整数
        IntStream zeroToNinetyNine = IntStream.range(0, 100);
        //生成的流包容 [0,100]区间的所有整数
        IntStream zeroToHundred = IntStream.rangeClosed(0, 100);
    }

    //构建一个不包容任何元素的流
    private static void createEmptyStream() {
        //构建一个空的字符串流
        Stream<String> stream = Stream.empty();
        //构建一个空的整数流
        IntStream numbers = IntStream.empty();
    }

}
