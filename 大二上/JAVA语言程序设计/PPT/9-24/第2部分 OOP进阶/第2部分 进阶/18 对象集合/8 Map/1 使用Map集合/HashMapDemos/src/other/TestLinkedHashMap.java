package other;

import java.util.*;


public class TestLinkedHashMap {
    public static void main(String[] args) {
        //试一试，如果改为HashMap，看看结果有什么不一样？
        var scores = new LinkedHashMap<String, Integer>();
        scores.put("语文", 80);
        scores.put("数学", 76);
        scores.put("英文", 96);

        System.out.println(scores);
        //遍历scores里的所有的key-value对
        for (Object key : scores.keySet()) {
            System.out.print(key + "------>");
            System.out.print(scores.get(key) + "\n");
        }
    }
}
