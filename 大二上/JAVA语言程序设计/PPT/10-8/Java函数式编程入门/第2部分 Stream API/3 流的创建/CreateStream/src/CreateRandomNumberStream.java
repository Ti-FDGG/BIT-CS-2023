import java.util.Random;
import java.util.stream.Stream;

public class CreateRandomNumberStream {
    public static void main(String[] args) {
        useGenerate(1, 100);
        useRandomClass();
    }

    //使用generate()方法生成[from,to]之间的随机整数
    private static void useGenerate(int from, int to) {
        Stream.generate(Math::random)
                .limit(5)
                .map(doubleNum -> (int) (doubleNum * (to - from + 1)) + 1)
                .forEach(System.out::println);
    }

    //基于JDK所提供的Random类方法生成随机数流
    private static void useRandomClass() {
        //生成[0,100)区间中的随机整数
        new Random()
                .ints(0, 100)
                .limit(5)
                .forEach(System.out::println);

        //生成[100,200)区间内的随机浮点数
        new Random()
                .doubles(100, 200)
                .limit(5)
                .forEach(System.out::println);
    }
}
