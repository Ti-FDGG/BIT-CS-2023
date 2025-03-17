package iterator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListInsertComparisons {

    //将dataToAdd集合中的所有元素，使用List.add方法，
    //全部插入到List集合的中部，统计插入耗费的时间
    public static long insertMid(List<String> list,
                                 List<String> dataToAdd) {
        int target = list.size() / 2;
        long start = System.nanoTime();

        for (String s : dataToAdd) {
            list.add(target++, s);
        }
        long end = System.nanoTime();
        return end - start;
    }

    //将dataToAdd集合中的所有元素，使用ListIterator.add方法，
    //全部插入到List集合的中部，统计插入耗费的时间
    public static long insertIter(List<String> list,
                                  List<String> dataToAdd) {
        int target = list.size() / 2;
        long start = System.nanoTime();
        ListIterator<String> iter = list.listIterator(target);
        for (String s : dataToAdd) {
            iter.add(s);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {

        //生成25万个整数，追加到dataToInsert这个集合中
        final int COUNT = 250_000;
        String [] items = new String[COUNT];
        for (int i = 0; i < COUNT; i++) {
            items[i] = "" + i;
        }
        List<String> dataToInsert = Arrays.asList(items);

        //分别以两种方式，将25万个整数插入到LinkedList中

        //方式一：使用ListIterator.Add()方法
        List<String> linkedList = new LinkedList<>();
        linkedList.addAll(dataToInsert);
        System.out.printf("使用ListIterator向LinkedList中部插入250000个元素，耗时："
                        + "%,12.6f secs\n",
                insertIter(linkedList, dataToInsert) / 1_000_000_000.0);

        //方式二：使用List.Add()方法
        linkedList = new LinkedList<>();
        linkedList.addAll(dataToInsert);
        System.out.printf("使用List.Add向LinkedList中部插入250000个元素，耗时："
                + "%,12.6f secs\n",
                insertMid(linkedList, dataToInsert) / 1_000_000_000.0);


    }
}
