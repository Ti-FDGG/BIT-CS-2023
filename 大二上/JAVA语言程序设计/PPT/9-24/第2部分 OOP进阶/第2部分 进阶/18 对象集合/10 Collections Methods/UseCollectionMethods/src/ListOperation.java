import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class ListOperation {
    public static void main(String[] args) {
        //创建两个颜色集合
        String[] colors = {"red", "white", "yellow", "blue"};
        List<String> list1 = Arrays.asList(colors);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("black");
        list2.add("red");
        list2.add("green");

        System.out.print("合并前, list2包容: ");
        System.out.println(list2);
        //将colors数组合并到list2集合
        Collections.addAll(list2, colors);
        System.out.print("\n合并后，list2包容: ");
        System.out.println(list2);

        // 获取单词 "red"的出现频数
        int frequency = Collections.frequency(list2, "red");
        System.out.printf(
                "\nlist2中red出现次数: %d\n", frequency);

        // 两个集合中是否包容有共同的元素
        boolean disjoint = Collections.disjoint(list1, list2);

        System.out.printf("list1和list2包容相同的元素？" + disjoint);
    }
}

