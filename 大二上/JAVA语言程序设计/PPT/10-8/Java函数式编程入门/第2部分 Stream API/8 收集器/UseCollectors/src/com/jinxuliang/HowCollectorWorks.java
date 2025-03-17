package com.jinxuliang;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HowCollectorWorks {
    public static void main(String[] args) {
        understandingCollect();
    }

    static void understandingCollect() {
        var nums = List.of(1, 2, 3, 4, 5, 6);

        var result = nums.stream().filter(num -> num > 3)
                .collect(Collectors.toList());
        System.out.println(result);

        //完全手工实现Collectors.toList()
        result = nums.stream().filter(num -> num > 3)
                .collect(() -> new ArrayList<Integer>(),
                        (list, element) -> list.add(element),
                        (list1, list2) -> list1.addAll(list2));
        System.out.println(result);
        //IntelliJ建议的优化
        result = nums.stream().filter(num -> num > 3)
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);
        System.out.println(result);
    }
}
