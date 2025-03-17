import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreeTypeMethodReference {
    public static void main(String[] args) {

        //原始方式
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(x -> System.out.println(x));
        //方式一：object::instanceMethod
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(System.out::println);

        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(System.out::println);
        //方式二：Class::staticMethod
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);


        List<String> strings =
                Arrays.asList("this", "is", "a", "list", "of", "strings");
        //原始方式
       strings.stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .forEach(System.out::println);
        //方式三：Class::instanceMethod
        //第一个参数作为对象引用，第二个（第三参数...）作为方法参数
       strings.stream()
               .sorted(String::compareTo)
               .forEach(System.out::println);
    }
}
