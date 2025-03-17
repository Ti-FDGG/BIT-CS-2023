package queue;

import list.EmptyListException;
import list.MyLinkList;

public class QueueInheritance extends MyLinkList<String> {
    public QueueInheritance() {
        super("queue");
    }

    public void enqueue(String o) {
        insertAtBack(o);
    }

    public String dequeue()
            throws EmptyListException {
        return removeFromFront();
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public void print() {
        super.print();
    }

    public static void main(String[] args) {
        var objQueue = new QueueInheritance();

        objQueue.enqueue("a");
        objQueue.enqueue("b");
        objQueue.enqueue("c");
        objQueue.enqueue("d");
        objQueue.print();

        try {
            while (true) {
                System.out.println(objQueue.dequeue() + " dequeued");
                objQueue.print();
            }
        } catch (EmptyListException e) {
            System.err.println("\n" + e.toString());
        }
    }
}


