package stack;


import list.EmptyListException;
import list.MyLinkList;

//使用组合方式实现堆栈
public class StackComposition {
    //在内部组合一个链表对象，并设置为私有的
    private final MyLinkList<String> linkList;

    //在构造方法中实例化一个链表对象
    public StackComposition() {
        linkList = new MyLinkList<String>("stack");
    }

    //region "调用MyLinkList相应的功能实现堆栈的功能"
    public void push(String o) {
        linkList.insertAtFront(o);
    }

    public String pop() throws EmptyListException {
        return linkList.removeFromFront();
    }

    public boolean isEmpty() {
        return linkList.isEmpty();
    }

    public void print() {
        linkList.print();
    }
    //endregion

    public static void main(String[] args) {
        var objStack = new StackComposition();
        objStack.push("a");
        objStack.push("b");
        objStack.push("c");
        objStack.push("d");
        objStack.print();
        try {
            while (true) {
                System.out.println(objStack.pop() + " popped");
                objStack.print();
            }
        } catch (EmptyListException e) {
            System.err.println("\n" + e.toString());
        }
    }
}


