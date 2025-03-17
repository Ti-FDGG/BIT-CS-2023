


import java.util.*;

public class FixedSizeList {
    public static void main(String[] args) {

        List<String> fixedList = Arrays.asList(
                "One book",
                "Two book");
        //获取fixedList的实现类，将输出Arrays$ArrayList
        System.out.println(fixedList.getClass());
        //遍历fixedList的集合元素
        for (String s : fixedList) {
            System.out.println(s);
        }
        //试图增加、删除元素都将引发UnsupportedOperationException异常
        fixedList.add("Three book");
        fixedList.remove("One book");

        var fixedList2 = List.of(1, 2, 3);
        //输出：class java.util.ImmutableCollections$ListN
        System.out.println(fixedList2.getClass());
        //输出：[1, 2, 3]
        System.out.println(fixedList2);
    }
}
