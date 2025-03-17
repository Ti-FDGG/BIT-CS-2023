import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UseComparableInterface {

    //英文星期单词的集合，供测试用
    static List<String> weekDays = Arrays.asList("Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    public static void main(String[] args) {

         sortByItself();
        // sortByStringLength();
        // sortByStringLengthUseIntegerCompareMethod();
        // sortByStringLengthUseComparatorMethod();
        // sortByNaturalOrder();
        // sortByReverseOrder();
        // sortByLowerCase();
        // sortByLinkedCompartor();
    }

    static void sortByItself() {
        //依据字符串自己定义的大小比较规则进行排序
        Collections.sort(weekDays);
        weekDays.forEach(System.out::println);
    }

    static void sortByStringLength() {
        //定义一个按单词长度比较的比较器
        Comparator<String> stringLengthComparator = (s1, s2) -> {
            if (s1.length() > s2.length()) {
                return 1;
            } else if (s1.length() < s2.length()) {
                return -1;
            } else {
                return 0;
            }
        };
        weekDays.sort(stringLengthComparator);
        weekDays.forEach(System.out::println);
    }

    static void sortByStringLengthUseIntegerCompareMethod() {
        //定义一个按单词长度比较的比较器,使用Integer类现有的方法实现
        Comparator<String> stringLengthComparator = (s1, s2) -> {
            return Integer.compare(s1.length(), s2.length());
        };
        weekDays.sort(stringLengthComparator);
        weekDays.forEach(System.out::println);
    }


    static void sortByStringLengthUseComparatorMethod() {
        //定义一个按单词长度比较的比较器,使用Integer类现有的方法实现
        Comparator<String> stringLengthComparator =
                Comparator.comparingInt(String::length);
        weekDays.sort(stringLengthComparator);
        weekDays.forEach(System.out::println);
    }

    static void sortByNaturalOrder() {
        //按照自然顺序（字典序）排序，要求对象实现了Comparable接口
        weekDays.sort(Comparator.naturalOrder());
        weekDays.forEach(System.out::println);
    }

    static void sortByReverseOrder() {
        //按照自然顺序（字典序）反向排序，要求对象实现了Comparable接口
        weekDays.sort(Comparator.reverseOrder());
        weekDays.forEach(System.out::println);
    }

    static void sortByLowerCase() {
        //使用comparing()方法，将字符串转换为小写之后再比较大小
        weekDays.sort(Comparator.comparing(String::toLowerCase));
        System.out.println("按小写名称排序");
        weekDays.forEach(System.out::println);
    }

    static void sortByLinkedCompartor() {
        //按单词长度排序，如果长度相同则按字典序排序
        Comparator<String> linkedComparator = Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder());
        weekDays.sort(linkedComparator);
        weekDays.forEach(System.out::println);
    }


}
