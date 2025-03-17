
import java.util.*;

public class TestTreeSetError {
    public static void main(String[] args) {
       // canNotAdd();
       // canAdd();
        canNotAdd2();
    }

    static class Err {
        int value;
    }

    static void canNotAdd() {
        var ts = new TreeSet<Err>();
        //尝试向TreeSet集合中添加一个Err对象
        ts.add(new Err());
    }

    //让自身可以比较大小
    static class Err2 implements Comparable<Err2>{
        int value;
        @Override
        public int compareTo(Err2 o) {
            return Integer.compare(value, o.value);
        }
    }

    static void canAdd(){
        var ts = new TreeSet<Err2>();
        //尝试向TreeSet集合中添加一个Err2对象
        ts.add(new Err2());
    }


    static void canNotAdd2() {
        var ts = new TreeSet();
        //向TreeSet集合中添加两个不同类型的对象
        ts.add("example string");
        ts.add(new Date());
    }
}
