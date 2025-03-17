import model.Time;
import model.TimeComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class CollectionSort {
    public static void main(String[] args) {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        //将数组转换为集合
        List<String> list = Arrays.asList(suits);
        System.out.printf("原始未排序的集合: %s\n", list);
        //升序排序
        Collections.sort(list);
        //输出集合内容
        System.out.printf("升序排序之后: %s\n", list);
        // 降序排列，使用Comparator
        Collections.sort(list, Collections.reverseOrder());
        System.out.printf("降序排序之后: %s\n", list);
    }
}


