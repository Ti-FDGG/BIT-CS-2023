import java.util.function.Function;

public class MyMathFunc {
    public static Integer timesTwo(Integer x) {
        return x * 2;
    }

    public static Integer timesThree(Integer x) {
        return x * 3;
    }

    public static Integer timesFour(Integer x) {
        return x * 4;
    }

    //通过返回一个函数，这个函数可以取代上述的三个函数
    public static Function<Integer, Integer> createMultiplier(Integer y) {
        return (Integer x) -> x * y;
    }

    public static void main(String[] args) {
        Function<Integer, Integer> timesTwo = MyMathFunc.createMultiplier(2);
        Function<Integer, Integer> timesThree = MyMathFunc.createMultiplier(3);
        Function<Integer, Integer> timesFour = MyMathFunc.createMultiplier(4);

        System.out.println(timesTwo.apply(6));
        System.out.println(timesThree.apply(6));
        System.out.println(timesFour.apply(6));
    }
}
