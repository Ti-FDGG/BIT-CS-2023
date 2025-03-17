public class ListTest {
    public static void main(String[] args) {
        //创建链表对象，每个节点包容一个MyData数据对象
        var objList = new MyLinkList<MyData>();
        //向链表中插入数据
        objList.insertAtFront(new MyData("a"));
        objList.insertAtFront(new MyData("b"));
        objList.insertAtBack(new MyData("c"));
        objList.insertAtBack(new MyData("d"));
        //输出链表内容
        objList.print();

        var toSearch = new MyData("d");
       // toSearch = new MyData("链表中肯定不存在的节点");
        var found = objList.search(toSearch);
        if (found != null)
            System.out.println("找到了节点：" + found + "\n");
        else
            System.out.println(toSearch + "在链表中未找到。\n");

        try {
            MyData removedObj = objList.removeFromFront();
            System.out.println(removedObj.toString() + " 己被移除");
            objList.print();
            removedObj = objList.removeFromFront();
            System.out.println(removedObj.toString() + " 己被移除");
            objList.print();
            removedObj = objList.removeFromBack();
            System.out.println(removedObj.toString() + " 己被移除");
            objList.print();
            removedObj = objList.removeFromBack();
            System.out.println(removedObj.toString() + " 己被移除");
            objList.print();
        } catch (EmptyListException e) {
            System.err.println("\n" + e.toString());
        }
    }
}
//空链表异常
class EmptyListException extends RuntimeException {
    public EmptyListException(String name) {
        super("The " + name + " is empty");
    }
}

// 链表节点
class MyLinkListNode<T> {

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

// 链表类
class MyLinkList<T> {
    //首尾指针
    private MyLinkListNode<T> firstNode;
    private MyLinkListNode<T> lastNode;
    private final String name; // 链表的名字

    // 创建一个指定名字的空链表
    public MyLinkList(String s) {
        name = s;
        firstNode = lastNode = null;
    }

    // 默认名字为list
    public MyLinkList() {
        this("list");
    }

    // 在链表前部追加一个节点
    // 如果链表为空，则首尾指针均指向这一新加的节点
    // 如果不为空，则firstNode指针引用它，原来的firstNode成为后继节点
    public synchronized void insertAtFront(T insertItem) {
        if (isEmpty())
            firstNode = lastNode = new MyLinkListNode<T>(insertItem);
        else
            firstNode = new MyLinkListNode<T>(insertItem, firstNode);
    }

    // 在链表后部追加一个节点
    // 如果链表为空，firstNode和lastNode都引用它
    // 否则，让lastNode的next字段引用它，然后再更新lastNode，
    // 让它引用新加入的节点
    public synchronized void insertAtBack(T insertItem) {
        if (isEmpty())
            firstNode = lastNode = new MyLinkListNode<T>(insertItem);
        else
            lastNode = lastNode.next = new MyLinkListNode<T>(insertItem);
    }

    // 移除第一个节点
    public synchronized T removeFromFront() throws EmptyListException {
        T removeItem = null;
        //如果节点为空，尝试移除，将引发“空链表异常”
        if (isEmpty())
            throw new EmptyListException(name);

        removeItem = firstNode.getData(); //

        // 重置 firstNode 和 lastNode 引用
        if (firstNode.equals(lastNode))
            firstNode = lastNode = null;
        else
            firstNode = firstNode.next;

        return removeItem;
    }

    // 移除最后一个节点
    public synchronized T removeFromBack() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException(name);
        T removeItem = lastNode.getData();
        // 重置firstNode和lastNode引用
        if (firstNode.equals(lastNode))
            firstNode = lastNode = null;
        else {
            MyLinkListNode<T> current = firstNode;
            while (current.next != lastNode)
                // 不是最后一个
                current = current.next; // 引用下一节点
            lastNode = current;
            current.next = null;
        }
        return removeItem;
    }

    // 链表是否为空？
    public synchronized boolean isEmpty() {
        return firstNode == null;
    }

    // 输出链表内容
    public synchronized void print() {
        if (isEmpty()) {
            System.out.println("Empty " + name);
            return;
        }

        System.out.print("链表 " + name + " 内容: ");

        var current = firstNode;

        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.getData());
            if (current.next != null) {
                sb.append(" -> ");
            } else {
                sb.append("\n");
            }
            current = current.next;
        }
        System.out.println(sb.toString());
    }

    public T search(T searchData) {
        if (isEmpty()) {
            return null;
        }
        MyLinkListNode<T> current = firstNode;
        while (current != null) {
            if (current.getData().equals(searchData)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
}

//封装链表节点所关联的数据
class MyData implements Comparable<MyData> {
    String data;

    @Override
    public int compareTo(MyData o) {
        return data.compareTo(o.data);
    }

    public MyData(String data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.compareTo((MyData) obj) == 0;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "'" + data + '\'' +
                '}';
    }
}
