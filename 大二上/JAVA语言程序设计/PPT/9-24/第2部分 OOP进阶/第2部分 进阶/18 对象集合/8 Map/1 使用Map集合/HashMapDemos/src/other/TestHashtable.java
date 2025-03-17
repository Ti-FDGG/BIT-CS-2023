package other;

import java.util.*;

//用于HashTable的Key
class MyKey {
    int count;
    public MyKey(int count) {
        this.count = count;
    }
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == MyKey.class) {
            MyKey a = (MyKey) obj;
            return this.count == a.count;
        }
        return false;
    }
    public int hashCode() {
        return this.count;
    }
    public String toString() {
        return "MyKey:" + this.count;
    }
}

//用于HashTable的Value
class MyValue {
    //这里出于演示的目的，让它的equals方法始终返回true
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

public class TestHashtable {
    public static void main(String[] args) {

        //创建一个HashTable对象，
        //此HasTable的Value类型为Object，
        //所以可以接收任何一种类型的数据
        var hashtable = new Hashtable<MyKey, Object>();
        //填充数据
        hashtable.put(new MyKey(60000), "Hello");
        hashtable.put(new MyKey(87563), "World");
        hashtable.put(new MyKey(1232), new MyValue());

        //HashTable的toString()方法，可以显示出它所包容的所有元素
        //{
        // MyKey:60000=Hello,
        // MyKey:87563=World,
        // MyKey:1232=MyValue@4e50df2e
        //}
        System.out.println(hashtable);

        //只要两个对象通过equals比较返回true，Hashtable就认为它们是相等的value。
        //因为Hashtable中有一个MyValue对象，它与任何对象通过equals比较都相等，
        //所以下面这句代码输出true。
        System.out.println(hashtable.containsValue("测试字符串"));
        //只要两个MyKey对象的count属性相等，它们通过equals比较返回true，且hashCode相等
        //Hashtable即认为它们是相同的key，所以下面输出true。
        System.out.println(hashtable.containsKey(new MyKey(87563)));
        //下面语句可以删除最后一个key-value对
        hashtable.remove(new MyKey(1232));
        for (Object key : hashtable.keySet()) {
            System.out.print(key + "---->");
            System.out.print(hashtable.get(key) + "\n");
        }
    }
}
