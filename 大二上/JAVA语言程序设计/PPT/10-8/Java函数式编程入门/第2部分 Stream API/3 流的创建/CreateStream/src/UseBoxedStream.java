import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UseBoxedStream {
    public static void main(String[] args) {
        //以下代码无法编译
        //IntStream.of(3, 1, 4, 1, 5, 9)
        //        .collect(Collectors.toList());

        //解决之道：将int用boxed方法转换为Integer，就可以编译了
        List<Integer> ints = IntStream.of(3, 1, 4, 1, 5, 9)
                .boxed()
                .collect(Collectors.toList());
        //输出：[3, 1, 4, 1, 5, 9]
        System.out.println(ints);

        //另一种方式：使用mapToObject，将其转换为对象
        List<Integer> ints2 = IntStream.of(3, 1, 4, 1, 5, 9)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
        //输出：[3, 1, 4, 1, 5, 9]
        System.out.println(ints2);

    }
}
