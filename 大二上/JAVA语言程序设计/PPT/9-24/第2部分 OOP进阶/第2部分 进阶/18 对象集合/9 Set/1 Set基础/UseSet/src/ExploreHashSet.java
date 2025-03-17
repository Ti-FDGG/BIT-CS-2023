import java.util.*;

class R {
    int value;
    public R(int value) {
        this.value = value;
    }
    public boolean equals(Object obj) {
        if (obj instanceof R) {
            R r = (R) obj;
            return r.value == this.value;
        }
        return false;
    }
    public int hashCode() {
        return this.value;
    }

    public String toString() {
        return "R(value:" + value + ",hashCode:"
                + this.hashCode() + ")";
    }
}

public class ExploreHashSet {
    public static void main(String[] args) {
        HashSet<R> hashSet = new HashSet<>();
        hashSet.add(new R(5));
        hashSet.add(new R(-3));
        hashSet.add(new R(9));
        hashSet.add(new R(-2));
        //打印HashSet集合的当前内容
        System.out.println("集合中的原始元素：" + hashSet);
        //取出第一个元素
        Iterator<R> it = hashSet.iterator();
        R first = it.next();
        //为第一个元素的value属性赋值
        first.value = -3;
        //再次输出value将看到HashSet里出现了两个Key一样的元素
        System.out.println("将第一个元素值改为-3：" + hashSet);
        //移除-3的元素
        hashSet.remove(new R(-3));
        System.out.println("移除-3的元素之后：" + hashSet);
        System.out.println("hashSet是否包含value为-3的R对象？"
                + hashSet.contains(new R(-3)));//false
    }
}
