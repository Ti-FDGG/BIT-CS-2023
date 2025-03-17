import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateFibonacci {
    public static void main(String[] args) {
        getFibonacciUseIterator(10);
        getFibonacciUseGenerate(10);
    }

    private static void getFibonacciUseIterator(int limit) {
        //斐波那契元组序列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(limit)    //生成20个元素
                .map(t -> t[0])  //只提取出数组中的第1个数（它就是要求的斐波那契数）
                .forEach(System.out::println);
    }

    static void getFibonacciUseGenerate(int limit) {
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib)
                .limit(limit)
                .forEach(System.out::println);
    }

}
