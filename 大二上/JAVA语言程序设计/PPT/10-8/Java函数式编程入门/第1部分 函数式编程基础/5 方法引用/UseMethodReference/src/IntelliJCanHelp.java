import java.util.function.BinaryOperator;
import java.util.function.Function;

public class IntelliJCanHelp {
    public static void main(String[] args) {
        //将鼠标移到高亮显示的部分，可以看到IntelliJ给的提示
        BinaryOperator<Integer> sum = (x, y) -> x + y;
        //输出：300
        System.out.println(sum.apply(100,200));
    }
}
