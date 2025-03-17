package com.jinxuliang;

import com.jinxuliang.model.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilterAndFindInStreamDemo {
    static Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        useFilter();
        //useFilter2();

        //useDistinct();

        //findAny(numbers);
        //findFirst(numbers);

        //useAllMatch();
        //useNoneMatch();
        //useAnyMatch();
        //useFindAny();

        //useTakeWhile();
        //useDropWhile();
    }

    static void useFilter() {
        var nums = List.of(1, 2, 3, 4, 5, 6);
        //过滤出所有奇数并输出
        nums.stream().filter(num -> num % 2 == 1)
                .forEach(System.out::println);
    }

    static void useFilter2() {
        //过滤出所有女性员工
        Person.persons().stream()
                .filter(Person::isFemale)
                .forEach(System.out::println);
        System.out.println();
        //级联filter，过滤出男性且收入大于5000的员工
        Person.persons().stream()
                .filter(Person::isMale)
                .filter(p -> p.getIncome() > 5000.0)
                .forEach(System.out::println);
        System.out.println();
        //使用复合逻辑表达式，完成同样的过滤功能
        Person.persons().stream()
                .filter(p -> p.isMale() && p.getIncome() > 5000.0)
                .forEach(System.out::println);
    }

    //消除重复项
    static void useDistinct() {
        var numbers =
                Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .distinct()
                .forEach(System.out::println);
    }

    static void findAny(Integer[] numbers) {
        //findAny的返回值不是确定的（尤其是在多线程环境下）
        var anyResult = Arrays.stream(numbers)
                .filter(n -> n < 10)
                .findAny();
        //输出：1
        anyResult.ifPresent(System.out::println);
    }

    static void findFirst(Integer[] numbers) {
        Predicate<Integer> lessThan10 = n -> n < 10;
        //对于有序集合，findFirst结果是确定的
        var firstResult = Arrays.stream(numbers)
                .filter(lessThan10)
                .findFirst();
        //输出：1
        firstResult.ifPresent(System.out::println);
    }

    static void useAllMatch() {
        var words = List.of("One", "Two",
                "Three", "Four");
        //集合中的所有元素，是否都满足特定的约束条件？
        var result = words.stream()
                .allMatch(word -> word.length() >= 3);
        System.out.println(result);
    }

    static void useNoneMatch() {
        var words = List.of("One", "Two",
                "Three", "Four");
        //是否集合中的所有元素，都不以“t”结尾？
        var result = words.stream()
                .noneMatch(word -> word.endsWith("t"));
        System.out.println(result);
    }

    static void useAnyMatch() {
        var words = List.of("One", "Two",
                "Three", "Four");
        //集合中是否至少有一个单词以F打头？
        var result = words.stream()
                .anyMatch(word -> word.startsWith("F"));
        System.out.println(result);
    }




    static void useTakeWhile() {
        var nums = List.of(1, 2, 3, 4, 5, 6);
        //取出小于6的第1个偶数
        nums.stream()
                .takeWhile(num -> {
                    System.out.println("takeWhile处理：" + num);
                    return num < 6 && num % 2 != 0;
                })
                .forEach(System.out::println);
    }

    static void useDropWhile() {
        List<Integer> numbers = List.of(1, 2, 1, 3, 3, 2, 4);
        //删除集合前部所有小于3的元素，然后返回剩余的所有元素
        numbers.stream()
                .dropWhile(num -> num < 3)
                .forEach(System.out::println);
    }


}
