import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TransformStreamDemo {

    public static void main(String[] args) {
        useMap();
        //useFlatMap();
        //sortStream();
    }

    static void useMap() {
        //计算1~5的整数平方值
        IntStream.rangeClosed(1, 5)
                .map(n -> n * n)
                .forEach(System.out::println);
        //将字符串集合中的所有字符串，批量转换为小写
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("oranage");
        //转换为小写
        Stream<String> lowercaseWords = words.stream()
                .map(String::toLowerCase);
        lowercaseWords.forEach(System.out::println);
    }

    static void useFlatMap() {
        var words = List.of("Hello", "World");
        words.stream()
                .map(word -> word.split(""))
                //将每个String[]转换为Stream<String>，再展平
                //得到Stream<String>
                .flatMap(Arrays::stream)
                .distinct() //消除重复元素
                .forEach(System.out::println);
    }

    static void sortStream() {
        List<String> words = List.of(
                "apple", "oranage", "cat", "monkey");
        //按照单词的长度进行排序
        Stream<String> sortedWords =
                words.stream().sorted(
                        Comparator.comparing(String::length));
        sortedWords.forEach(System.out::println);
    }
}
