
import java.util.*;


public class NullInHashMap {
    public static void main(String[] args) {
        var hashMap = new HashMap<String, String>();
        //试图将2个key为null的key-value对放入HashMap中——后来者居上
        hashMap.put(null, "null value1");
        hashMap.put(null, "null value2");
        //输出：null value2
        System.out.println(hashMap.get(null));
        //将一个value为null的key-value对放入HashMap中
        hashMap.put("string key", null);
        //输出：{null=null value2, string key=null}
        System.out.println(hashMap);
    }
}
