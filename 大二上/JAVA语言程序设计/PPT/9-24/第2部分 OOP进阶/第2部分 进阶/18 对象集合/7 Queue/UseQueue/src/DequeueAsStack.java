import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class DequeueAsStack {
    public static void main(String[] args) {
        var original = Arrays.asList("One", "Two", "Three");
        Deque<String> stack = new ArrayDeque<>();
        //从List中取出所有的元素，push到堆栈中
        for (String s : original) {
            stack.push(s);
        }
        //不断地出栈,输出：Three|Two|One|
        while (stack.size() > 0) {
            System.out.print(stack.pop()+"|");
        }
    }
}
