
import java.util.function.Function;

public class CompositionDemo {
    public static void main(String[] args) {
        Function<Integer, Integer> timesTwo = x -> x * 2;
        Function<Integer, Integer> minusOne = x -> x - 1;
        //函数的级联
        Function<Integer, Integer> timesTwoMinusOne = timesTwo.andThen(minusOne);
        System.out.println(timesTwoMinusOne.apply(10));//19
        //函数的合成（先减后乘）
        Function<Integer, Integer> composition = timesTwo.compose(minusOne);
        System.out.println(composition.apply(10));//18
        //函数的合成（先乘后减）
        Function<Integer, Integer> composition2 = minusOne.compose(timesTwo);
        System.out.println(composition2.apply(10));//19
    }
}
