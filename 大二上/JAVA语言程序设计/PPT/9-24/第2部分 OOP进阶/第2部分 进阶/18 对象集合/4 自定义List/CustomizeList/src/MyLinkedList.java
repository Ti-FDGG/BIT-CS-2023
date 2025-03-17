import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//自定义实现LinkedList
public class MyLinkedList<E>  implements List<E> {

    //链表中的节点
    private class Node {
        Node next;
        E data;
    }

    //初始时，new一个头节点
    private Node head = new Node();

    //在尾部追加
    @Override
    public boolean add(E e) {
        Node newNode = new Node();
        newNode.data = e;
        //查找最后一个节点
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
        return true;
    }

    //在中间追加
    @Override
    public void add(int index, E element) {
        Node newNode = new Node();
        newNode.data = element;
        Node before = head;
        int i = index - 1;
        while (i-- >= 0) {
            before = before.next;
        }
        Node after = before.next;
        before.next = newNode;
        newNode.next = after;
    }

    //按位置提取
    @Override
    public E get(int index) {
        Node n = head;
        int i = index;
        while (i-- >= 0) {
            n = n.next;
        }
        return n.data;
    }

    @Override
    public E set(int index, E element) {
        Node n = head;
        int i = index;
        while (i-- >= 0) {
            n = n.next;
        }
        E old = n.data;
        n.data = element;
        return old;
    }

    @Override
    public E remove(int index) {
        Node before = head;
        int i = index - 1;
        while (i-- >= 0) {
            before = before.next;
        }
        Node thisOne = before.next;
        Node after = thisOne.next;
        before.next = after;
        return thisOne.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyLinkedList[");
        Node n = head;
        while (n.next != null) {
            n = n.next;
            sb.append(n.data);
            if (n.next != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
