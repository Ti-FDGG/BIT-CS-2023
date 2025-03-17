import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class UseQueue {
    public static void main(String[] args) {
        //ArrayDeque是JDK中常用的队列类型
        Queue<String> q = new ArrayDeque<>();
        q.add("One");
        q.add("Two");
        q.add("Three");
        q.add("Four");
        System.out.println("原始队列："+q);

        System.out.println("出队两个元素：");
        System.out.println("出队> " + q.remove());
        System.out.println("出队> " + q.poll());
        System.out.println("队列中剩余元素个数："+q.size());
        System.out.println(q);
        System.out.println("向队列中批量添加三个元素");
        var strList= List.of("Five","Six","Seven");
        q.addAll(strList);
        System.out.println(q);

        System.out.println("持续出队，直到队列为空……");
        while(!q.isEmpty()){
            System.out.println("item> " + q.remove());
        }
        System.out.println("队列为空时，再次出队，" +
                "将会抛出java.util.NoSuchElementException");
        q.remove();
    }
}
