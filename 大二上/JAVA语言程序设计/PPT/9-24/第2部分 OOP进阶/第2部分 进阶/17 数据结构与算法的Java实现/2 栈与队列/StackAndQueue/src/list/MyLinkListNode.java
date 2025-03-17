package list;

// 链表节点
public class MyLinkListNode<T> {

    private T data;
    MyLinkListNode<T> next;

    //独立的链表节点
    MyLinkListNode(T data) {
        this(data, null);
    }

    //有下一个节点的链表节点
    MyLinkListNode(T data, MyLinkListNode<T> nextNode) {
        this.data = data;
        next = nextNode; // 直接设定下一个节点
    }

    //region "getter and setter"
    T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(MyLinkListNode<T> next) {
        this.next = next;
    }

    public MyLinkListNode<T> getNext() {
        return next;
    }
    //endregion

}
