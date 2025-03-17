package stack;

import list.EmptyListException;
import list.MyLinkList;


//使用继承方式实现堆栈
public class StackInheritance
        extends MyLinkList<String> {
    //构造方法
    public StackInheritance() {
        super("stack");
    }

    //入栈
    public void push(String o) {
        insertAtFront(o);
    }

    //出栈
    public String pop()
            throws EmptyListException {
        return removeFromFront();
    }

    //栈是否为空？
    public boolean isEmpty() {
        return super.isEmpty();
    }

    //打印栈中的内容
    public void print() {
        super.print();
    }

    //测试自定义堆栈的功能
    public static void main(String[] args) {
        //创建一个堆栈对象
        var objStack = new StackInheritance();
        //向栈中压入三个字符串
        objStack.push("a");
        objStack.push("b");
        objStack.push("c");
        //输出栈的当前内容
        objStack.print();
        try {
            while (true) {
                //不断地出栈，直到栈空，抛出EmptyListException
                System.out.println(objStack.pop() + " 被弹出了！");
                //每弹出一个元素，输出一次栈中剩余的元素
                objStack.print();
            }
        } catch (EmptyListException e) {
            System.err.println("\n" + e.toString());
        }
    }
}


