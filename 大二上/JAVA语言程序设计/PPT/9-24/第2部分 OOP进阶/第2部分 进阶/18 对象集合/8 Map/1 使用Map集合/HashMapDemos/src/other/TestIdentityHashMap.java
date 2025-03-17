package other;

import java.util.*;

public class TestIdentityHashMap {
    public static void main(String[] args) {
        var hashMap = new IdentityHashMap<String, Integer>();
        //下面两行代码将会向IdentityHashMap对象中添加3个key-value对
        hashMap.put("语文", 89);
        hashMap.put(new String("语文"), 78);
        hashMap.put("java", 93);
        hashMap.put("java", 98);
        System.out.println(hashMap);
    }
}
