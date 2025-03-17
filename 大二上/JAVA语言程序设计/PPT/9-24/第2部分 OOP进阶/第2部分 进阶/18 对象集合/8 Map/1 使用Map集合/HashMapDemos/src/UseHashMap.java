import java.util.*;

public class UseHashMap {

    public static void main(String[] args) {
        //创建一个示例HashMap
        HashMap<String, Integer> hashMap = getStringIntegerHashMap();
        //展示如何迭代访问Map,以及如何在迭代过程中删除元素
        visitHashMap(hashMap);

        //重新生成集合，还原到最初状态以便演示后面的功能
        hashMap = getStringIntegerHashMap();
        //向Map中添加新的元素
        addElementToHashMap(hashMap);

        //提取Map中元素的N种方法
        visitSingleElementInMap(hashMap);

        //替换Map中元素的N种方法
        replaceElementInMap(hashMap);


    }

    private static void replaceElementInMap(HashMap<String, Integer> hashMap) {
        int oldValue = hashMap.replace("Key1", 1000);
        //输出：oldValue=1,newValue=1000
        System.out.println("oldValue=" + oldValue + ",newValue=" + hashMap.get("Key1"));
        //replace方法的另一个重载，要求Key和oldValue值都匹配，才更新其值
        boolean replaceResult = hashMap.replace("Key1", 100, 10000);
        //输出：false
        System.out.println(replaceResult);
        //这次操作应该成功，因为Key1当前对应的Value确实是1000
        replaceResult = hashMap.replace("Key1", 1000, 10000);
        //输出：true
        System.out.println(replaceResult);
        //输出：{Key2=100, Key1=10000, Key4=4, Key3=3, Random Number=78}
        System.out.println(hashMap);
        //将所有的元素值全部翻倍
        hashMap.replaceAll((key, currentValue)->currentValue*2);
        //输出：{Key2=200, Key1=20000, Key4=8, Key3=6, Random Number=124}
        System.out.println(hashMap);
    }

    private static void visitSingleElementInMap(HashMap<String, Integer> hashMap) {
        //访问元素需要指定key
        // int value = hashMap.get("Not Existed Key");
        //如果指定的key不存在，以下这句将引发NullPointerException，
        //因为get()返回一个null，而程序又尝试调用Integer.intValueOf()方法。
        //System.out.println(value);
        //为避免抛出异常，可以在Key不存在时，返回一个默认值
        int value = hashMap.getOrDefault("Not Existed Key", -1);
        //输出：-1
        System.out.println(value);

        //当指定的Key不存在时，使用Lambda表达式生成一个！
        int ranValue = hashMap.computeIfAbsent("Random Number",
                num -> new Random().nextInt(100));
        //某次输出：59
        System.out.println(ranValue);
        //此时，Map中的内容为：{Key2=100, Key1=1, Key4=4, Key3=3, Random Number=59}
        System.out.println(hashMap);
    }

    private static void addElementToHashMap(HashMap<String,Integer> hashMap) {

        System.out.println("\n原始集合中的元素");
        //输出：{Key2=2, Key1=1, Key4=4, Key3=3}
        System.out.println(hashMap);
        //可以多次向HashMap中追加相同Key的元素，“后来者居上”
        hashMap.put("Key2", 100);
        System.out.println("\n修改后的集合元素：");
        //输出：{Key2=100, Key1=1, Key4=4, Key3=3}
        System.out.println(hashMap);

    }

    //遍历集合的三种方式
    private static void visitHashMap(HashMap<String, Integer> hashMap) {

        System.out.println("\n使用KeySet遍历集合");

        Set<String> keySet = hashMap.keySet();
        for (String key : keySet) {
            System.out.println(key + ":" + hashMap.get(key));
            // 循环遍历过程中，不允许移除元素，否则，
            // 引发ConcurrentModificationException
            // if(hashMap.get(key) % 2!=0){
            // hashMap.remove(key);
            // }
        }

        System.out.println("\n使用Iterator遍历并移除元素");

        Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
        Iterator<Map.Entry<String, Integer>> itor = entrySet.iterator();
        while (itor.hasNext()) {
            Map.Entry<String, Integer> entry = itor.next();
            if (entry.getValue() % 2 != 0) {
                itor.remove(); //移除元素
                System.out.println(entry.getKey() + ":" +
                        entry.getValue() + "已被移除");
            } else {
                System.out.println(entry.getKey() + ":"
                        + entry.getValue());
            }
        }

        //Java 8引入了Lambda表达式，所以，可以使用它来简化遍历代码
        hashMap.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    //创建一个示例用的HashMap<String,Integer>
    private static HashMap<String, Integer> getStringIntegerHashMap() {
        //创建HashMap对象
        HashMap<String, Integer> hashMap = new HashMap<>();
        //向其中添加四个元素
        for (int i = 1; i < 5; i++) {
            hashMap.put("Key" + i, i);
        }
        //输出：{Key2=2, Key1=1, Key4=4, Key3=3}
        System.out.println(hashMap);
        return hashMap;
    }

}

