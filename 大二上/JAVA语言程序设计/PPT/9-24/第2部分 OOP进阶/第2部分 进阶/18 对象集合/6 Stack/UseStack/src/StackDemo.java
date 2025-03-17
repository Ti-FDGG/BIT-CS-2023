import java.util.Stack;


public class StackDemo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("One");
        stack.push("Two");
        //One和Two入栈之后，当前栈顶: Two
        System.out.println("One和Two入栈之后，当前栈顶: " + stack.peek());
        stack.pop();
        //出栈一次之后，当前栈顶: One
        System.out.println("出栈一次之后，当前栈顶: " + stack.peek());
        stack.push("Three");
        //Three入栈之后，当前栈顶: Three
        System.out.println("Three入栈之后，当前栈顶: " + stack.peek());
        //持续出栈，直到栈空
        while (!stack.empty()) {
            System.out.println("出栈：" + stack.pop());
        }
        System.out.println("栈空？" + stack.isEmpty());
        //堆栈为空，再次pop会抛出EmptyStackException
        stack.pop();
    }
}
