package com.jinxuliang;

import com.jinxuliang.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UseCollectors {
    public static void main(String[] args) {
        distinctWithSet();
//        useStatisticsCollectors();
//        useStringCollectors();
//        useMappingCollector();
//        useMinByCollector();
        useStatisticsCollectors();
//        useGroupCollectors();
//        usePartitioningCollectors();
    }

    //使用收集器将Stream转换为Set，从而自动地消除重复元素
    static void distinctWithSet() {
        var nums = List.of(1, 1, 3, 5, 9, 3, 8);
        Set<Integer> distinctNumbers = nums.stream()
                .collect(Collectors.toSet());

        System.out.println(distinctNumbers);
    }

    static void useStringCollectors() {
        var numbers = List.of(1, 2, 3, 4, 5,
                6, 5, 4, 3, 2, 1);
        //字符串连接收集器
        var numString = numbers.stream()
                .map(num -> String.valueOf(num))
                .collect(Collectors.joining(","));
        System.out.println(numString);
    }

    static void useMappingCollector() {
        var students = Student.getExampleStudents();
        //用于提取Student对象的name字段值
        Function<Student, String> getStudentName = Student::getName;
        //将每个Student对象先转换为字符串，再收集到List中
        List<String> nameList = students
                .stream()
                .collect(Collectors.mapping(getStudentName, Collectors.toList()));
        System.out.println(nameList);
    }

    static void useMinByCollector() {
        var students = Student.getExampleStudents();
        //提取出学生的Gpa成绩
        var gpaCompartor = Comparator.comparing(Student::getGpa);
        //获取Gpa最低的那名学生的信息
        var minResult = students.stream().collect(
                Collectors.minBy(
                        gpaCompartor
                ));
        minResult.ifPresent(System.out::println);
    }




    //分组收集器
    static void useGroupCollectors() {

        var numbers = List.of(1, 2, 3, 4, 5,
                6, 5, 4, 3, 2, 1);

        //使用groupingBy()收集器对数据进行分组，此方法接收的Lambda表达式，
        //必须依据输入值输出有限个数的固定值，它将成为分组的标准
        var groupResult = numbers.stream()
                .collect(Collectors.groupingBy(num -> {
                    if (num % 2 == 0) {
                        return "even"; //分到偶数组
                    }
                    return "odd"; //分到奇数组
                }));
        System.out.println(groupResult);

        //先分组,再过滤
        //只对值大于50以上的元素进行分组
        var evenAndOddNumbersGreateThan50 =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .collect(Collectors.groupingBy(num -> {
                            if (num % 2 == 0) {
                                return "even";
                            }
                            return "odd";
                        }, Collectors.filtering(
                                num -> num.intValue() >= 50,
                                Collectors.toList())));

        System.out.println(evenAndOddNumbersGreateThan50);

        //分组，同时统计每组元素个数
        var groupCounting = numbers.stream()
                .collect(Collectors.groupingBy(num -> {
                    if (num % 2 == 0) {
                        return "even";
                    }
                    return "odd";
                }, Collectors.counting()));
        System.out.println(groupCounting);
    }

    //分区收集器
    static void usePartitioningCollectors() {

        //依据考试成绩，分为“及格”与“不及格”两组
        var doYouPassed =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .collect(Collectors.partitioningBy(
                                score -> score >= 60));
        System.out.println(doYouPassed);

        //先分区,再进一步分组
        var doYouPassed2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .collect(Collectors.partitioningBy(
                                score -> score >= 60,
                                Collectors.groupingBy(score -> {
                                    int temp = score / 10;
                                    String result = switch (temp) {
                                        case 0, 1, 2, 3, 4, 5 -> "不及格";
                                        case 6 -> "及格";
                                        case 7 -> "中";
                                        case 8 -> "良";
                                        case 9, 10 -> "优";
                                        default -> "无效成绩";
                                    };
                                    return result;
                                })));
        System.out.println(doYouPassed2);
    }

}
