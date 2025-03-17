import java.util.PriorityQueue;
import java.util.Queue;

public class TestPriorityQueue {
    public static void main(String[] args) {
       showPriorityQueueContent();
      //  pollAll();
      //peekPollAndRemove();
    }

    private static void peekPollAndRemove() {
        var priorityQueue = getExamplePriorityQueue();
        System.out.println("队列中原有成员：");
        System.out.println(priorityQueue);
        //访问队列第一个元素(不出队），其实就是队列中最小的元素：-3
        System.out.println("\n查询最小元素（在队头）："+priorityQueue.peek());
        //从队首移除一个元素
        priorityQueue.poll();
        System.out.println("\n移除队列中的head元素之后");
        System.out.println(priorityQueue);
        //查找并移除元素9（由于有重复的，仅移除第一个）
        priorityQueue.remove(9);
        System.out.println("\n 移除队列中的元素9之后");
        System.out.println(priorityQueue);
        //又加入一个新元素56
        priorityQueue.offer(56);
        System.out.println("\n 向队列中添加元素56之后");
        System.out.println(priorityQueue);
    }

    //查看队列内容而不出队
    private static void showPriorityQueueContent() {
        var priorityQueue = getExamplePriorityQueue();
        //foreach迭代访问优先级队列
        //输出：-3,0,9,6,110,9,
        for (var num : priorityQueue) {
            System.out.print(num + ",");
        }
        System.out.println();
        //使用iterator访问优先级队列
        //输出：-3,0,9,6,110,9,
        var iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }
        System.out.println();
        //最方便的方法，调用toString()方法得到队列内容
        //输出：[-3, 0, 9, 6, 110, 9]
        System.out.println(priorityQueue);
    }

    //队列中的所有元素，全部出队
    private static void pollAll() {
        var priorityQueue = getExamplePriorityQueue();
        //依次出队列，可以看到，并不是按元素的加入顺序排列，
        //而是按元素的大小顺序出队的
        //输出：-3,0,6,9,9,110,
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + ",");
        }
        System.out.println();
        //现在队列为空，输出：[]
        System.out.println(priorityQueue);
    }

    //创建一个测试用的优先级队列
    private static Queue<Integer> getExamplePriorityQueue() {
        Queue<Integer> pq = new PriorityQueue<>();
        //下面代码依次向pq中加入6个元素，其中有重复的
        pq.offer(6);
        pq.offer(-3);
        pq.offer(9);
        pq.offer(0);
        pq.offer(110);
        pq.offer(9);
        return pq;
    }

}
