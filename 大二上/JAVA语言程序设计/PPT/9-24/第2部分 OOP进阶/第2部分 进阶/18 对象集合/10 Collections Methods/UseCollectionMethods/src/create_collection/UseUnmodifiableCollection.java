package create_collection;

import java.util.*;


public class UseUnmodifiableCollection {
    public static void main(String[] args) {
        //创建一个空的、不可改变的List对象
        List<String> unmodifiableList = Collections.emptyList();
        //创建一个只有一个元素，且不可改变的Set对象
        Set<String> unmodifiableSet = Collections.singleton("singleton");
        //创建一个普通Map对象
        Map<String, Integer> scores = new HashMap<>();
        scores.put("语文", 80);
        scores.put("Java", 82);
        //返回普通Map对象对应的不可变版本
        Map<String, Integer> unmodifiableMap =
                Collections.unmodifiableMap(scores);
        //下面任意一行代码都将引发UnsupportedOperationException异常
        unmodifiableList.add("新元素");
        unmodifiableSet.add("新元素");
        unmodifiableMap.put("语文", 90);
    }
}

