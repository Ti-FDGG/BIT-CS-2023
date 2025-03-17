import java.util.function.BiFunction;
import java.util.function.Function;

public class HighOrderDemo {

    static TriFunction<Integer, Integer, Integer, Integer> add =
            (x, y, z) -> x + y + z;

    public static void main(String[] args) {
        demo1();

        demo2();

        demo3();
    }

    //参数拆成2+1的形式
    private static void demo2() {
        //定义一个返回Function的高阶函数
        BiFunction<Integer, Integer, Function<Integer, Integer>> addPartial =
                (x, y) -> (z) -> add.apply(x, y, z);
        //先传入两个参数
        Function<Integer, Integer> add1And2 = addPartial.apply(1, 2);
        //再传入剩余一个参数
        var result = add1And2.apply(3);
        //1+2+3=6
        System.out.println(result); //6
    }

    //参数拆成1+2的形式
    private static void demo1() {
        //定义一个返回BiFunction的高阶函数
        Function<Integer, BiFunction<Integer, Integer, Integer>> addPartial =
                (x) -> (y, z) -> add.apply(x, y, z);
        //先传入一个参数
        BiFunction<Integer, Integer, Integer> add1 = addPartial.apply(1);
        //再传入剩余两个参数
        var result = add1.apply(2, 3);
        //1+2+3=6
        System.out.println(result); //6
    }

    //参数拆成1+1+1的形式
    private static void demo3() {
        //嵌套三层的函数定义
        Function<Integer, Function<Integer, Function<Integer, Integer>>> myAdd =

                x -> (y) -> z -> x + y + z;
        //级联相加
        var result = myAdd.apply(1).apply(2).apply(3);
        System.out.println(result);
    }
}
