import model.ModelHelper;
import model.MyClass;

import java.util.HashMap;

//KeySet，Values和Map.Entry，其实，都可以看成是对底层集合元素的一种“映射”
//或者称为一种”视图“
public class UseMapView {
    public static void main(String[] args) {
        var myMap = new HashMap<Integer, MyClass>();
        for (int i = 0; i < 5; i++) {
            myMap.put(i, ModelHelper.createMyClass());
        }
        System.out.println(myMap);

        var keys = myMap.keySet();
        System.out.println(keys);
        //移除了key,原有的集合也移除了对应的元素
        keys.remove(1);
        System.out.println(keys);
        System.out.println(myMap);

        var values = myMap.values();
        System.out.println(values);
        //移除了value,原有的集合也移除了对应的元素
        values.remove(values.toArray()[1]);
        System.out.println(values);
        System.out.println(myMap);

        System.out.println("\n---entrySet---\n");
        var entries
                = myMap.entrySet();
        for (var entry : entries) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        //移除了entry,原有的集合也移除了对应的元素
        entries.remove(entries.toArray()[1]);
        for (var entry : entries) {
            System.out.println(entry.getKey() + ":"
                    + entry.getValue());
        }
    }
}
