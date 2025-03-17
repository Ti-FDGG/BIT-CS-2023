import java.util.*;

class R implements Comparable {
    int value;

    public R(int value) {
        this.value = value;
    }

    public String toString() {
        return "R(value属性:" + value + ")";
    }

    //重写了equals方法，如果value属性相等返回true
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == R.class) {
            R r = (R) obj;
            if (r.value == this.value) {
                return true;
            }
        }
        return false;
    }


    //重写了compareTo(Object obj)方法，如果value属性相等返回0;
    public int compareTo(Object obj) {
        R r = (R) obj;
        if (this.value > r.value) {
            return 1;
        } else if (this.value == r.value) {
            return 0;
        } else {
            return -1;
        }
    }
}

public class UseTreeMap {
    public static void main(String[] args) {

        var treeMap = new TreeMap<R, String>();
        treeMap.put(new R(3), "Key值为3的value");
        treeMap.put(new R(-5), "Key值为-5的value");
        treeMap.put(new R(9), "Key值为9的value");
        System.out.println("TreeMap集合中的所有元素：\n" + treeMap);

        //返回该TreeMap的第一个Entry对象
        System.out.println("\n第一个Entry对象：" + treeMap.firstEntry());
        //返回该TreeMap的最后一个key值
        System.out.println("最后一个key值:" + treeMap.lastKey());
        //返回该TreeMap的比new R(2)大的最小key值。
        System.out.println("比new R(2)大的最小key值:" +
                treeMap.higherKey(new R(2)));
        //返回该TreeMap的比new R(2)小的最大的key－value对。
        System.out.println("比new R(2)小的最大的key－value对:" +
                treeMap.lowerEntry(new R(2)));
        //返回该TreeMap的子TreeMap
        System.out.println("该TreeMap的子TreeMap:" +
                treeMap.subMap(new R(-1), new R(4)));

    }
}
