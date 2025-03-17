package create_collection;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

public class UseOfMethods {
    public static void main(String[] args) {
        useListOf();
        useMapOf();
        useSetOf();
        useMapOfEntries();
    }

    static void useListOf() {
        List<String> list = List.of("One", "Two", "Three");
        //输出：class java.util.ImmutableCollections$ListN
        System.out.println(list.getClass());
        //迭代访问
        list.forEach(System.out::println);
        //使用of系列方法创建的集合是只读的，创建完毕之后
        //尝试向其添加元素，将抛出UnsupportedOperationException
        //list.add("Four");
    }

    static void useMapOf() {
        Map<String, LocalDate> specialDays = Map.of(
                "国庆节", LocalDate.of(1949, Month.OCTOBER, 1),
                "五一节", LocalDate.of(1886, Month.MAY, 1)
        );
        //class java.util.ImmutableCollections$MapN
        System.out.println(specialDays.getClass());
        specialDays.forEach((k, v) -> System.out.println(k + " on " + v));
    }

    static void useSetOf() {
        Set<String> set = Set.of("One", "Two", "Three");
        //class java.util.ImmutableCollections$SetN
        System.out.println(set.getClass());
        //输出：Set is [Three, Two, One]
        System.out.println("Set is " + set);
    }

    static void useMapOfEntries() {
        Map<Integer, String> mapEntries = Map.ofEntries(
                entry(3, "Three"),
                entry(7, "Seven"),
                entry(1, "One")
        );
        //class java.util.ImmutableCollections$MapN
        System.out.println(mapEntries.getClass());
        mapEntries.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
