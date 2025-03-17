import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UseStreamAPI {
    public static void main(String[] args) {
        //原始数据集合
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        //构建Stream对象
        var nums = Arrays.stream(arr);
        //使用Stream API构建的数据处理流水线
        var evenNumbers = nums
                //过滤
                .filter(num -> num % 2 == 0)
                //装箱为Intger流
                .boxed()
                //Integer转换为字符串
                .map(Object::toString)
                //收集所有字符串，以“,“分隔，连接起来，构建一个字符串
                .collect(Collectors.joining(", "));
        //输出：2, 4, 6, 8
        System.out.println(evenNumbers);
    }
}
