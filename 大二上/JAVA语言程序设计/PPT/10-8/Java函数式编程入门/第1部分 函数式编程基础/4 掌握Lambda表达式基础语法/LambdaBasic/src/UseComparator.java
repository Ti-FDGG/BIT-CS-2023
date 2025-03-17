

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UseComparator {

    public static void main(String args[]) {

        List<String> strings = new ArrayList<String>();
        strings.add("AAA");
        strings.add("bbb");
        strings.add("CCC");
        strings.add("ddd");
        strings.add("EEE");

        //使用Lambda表达式重写上述代码段
        Comparator<String> comparator = (str1, str2) -> {
            return str1.compareToIgnoreCase(str2);
        };
        Collections.sort(strings, comparator);
        System.out.println("Sort with comparator");

        //输出排序结果
        for (String str : strings) {
            System.out.println(str);
        }
    }
}


