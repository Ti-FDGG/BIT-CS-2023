import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class BinarySearchDemo {
    public static void main(String[] args) {
        //原始数组
        String[] colors = {"red", "white", "blue", "black", "yellow",
                "purple", "tan", "pink"};
        //转换为集合
        List<String> list =
                new ArrayList<>(Arrays.asList(colors));

        //先排序
        Collections.sort(list);
        System.out.printf("排序之后: %s\n", list);

        // 二分查找演示
        searchElement(list, colors[3]);
        searchElement(list, colors[0]);
        searchElement(list, colors[7]);

        searchElement(list, "aqua");
        searchElement(list, "gray");
        searchElement(list, "teal");
    }

    //使用二分法在己排好序的集合中查找元素
    private static void searchElement(
            List<String> list, String element) {
        int result = 0;
        System.out.printf("\n二分查找: %s\n", element);
        result = Collections.binarySearch(list, element);
        if (result >= 0)
            System.out.printf("要查找的元素位置索引为：%d\n", result);
        else
            System.out.printf("没有找到 (%d)\n", result);
    }
}

