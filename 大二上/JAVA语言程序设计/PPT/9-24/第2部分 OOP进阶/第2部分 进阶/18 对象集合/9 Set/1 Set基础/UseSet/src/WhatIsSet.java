
import java.util.*;

public class WhatIsSet {
    public static void main(String[] args) {
        onlyOne();
        removeDuplicateElements();
    }

    private static void onlyOne() {
        //创建一个包容字符串的Set集合
        Set<String> books = new HashSet<>();
        //添加一个字符串对象
        books.add("Hello");
        //再次添加一个字符串对象，
        //因为两个字符串对象通过equals方法比较相等，所以添加失败，返回false
        boolean result = books.add(new String("Hello"));
        System.out.println(result);//false
        //下面输出看到集合只有一个元素
        System.out.println(books);//[Hello]
    }

    //使用这种方法，可以轻松地移除重复元素
    private static void removeDuplicateElements() {
        //Set中不允许有重复的元素
        Set<String> stringSet = new HashSet<>();
        String[] strings = {"a", "b", "c", "c"};
        stringSet.addAll(Arrays.asList(strings));
        //输出：[a, b, c]
        System.out.println(stringSet);
    }
}