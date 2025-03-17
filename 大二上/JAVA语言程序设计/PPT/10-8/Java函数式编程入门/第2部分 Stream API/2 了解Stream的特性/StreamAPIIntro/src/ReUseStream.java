import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class ReUseStream {

    public static void main(String[] args) {
        StreamCannotBeReuse();
//       StreamMustBeRecreated();
//        StreamRecreatedViaSupplier();

    }

    //试图重用一个Stream，引发异常
    private static void StreamCannotBeReuse() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = numbers.stream();
        System.out.println("numbers集合中有元素" + stream.count() + "个");
        //以下这句将引发java.lang.IllegalStateException
        System.out.println("平均值为：" +
                stream.mapToInt(num -> num.intValue()).average());
    }

    //只有被重建之后，才能再次执行操作
    private static void StreamMustBeRecreated() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = numbers.stream();
        System.out.println("numbers集合中有元素" + stream.count() + "个");
        //必须重建一个流对象，否则，尝试重用流，会抛出IllegalStateException异常
        stream = numbers.stream();
        System.out.println("平均值为："
                + stream.mapToInt(Integer::intValue)
                .average().getAsDouble());
    }

    //通过构建一个Supplier对象，重复创建特定的流
    private static void StreamRecreatedViaSupplier() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Supplier<IntStream> streamSupplier =
                () -> numbers.stream().mapToInt(Integer::intValue);
        //使用Supplier接口所定义的get()方法，获取流对象的新实例
        System.out.println("numbers集合中有元素"
                + streamSupplier.get().count() + "个");
        System.out.println("平均值为："
                + streamSupplier.get().average());
    }
}
