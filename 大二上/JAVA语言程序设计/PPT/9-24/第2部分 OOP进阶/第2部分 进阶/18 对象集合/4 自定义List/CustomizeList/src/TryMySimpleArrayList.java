import java.util.Iterator;
import java.util.List;

public class TryMySimpleArrayList {
    public static void main(String[] args) {
        List<String> ls = new MySimpleArrayList<>();
        ls.add("张三");
        ls.add("李四");
        ls.add("王五");
        System.out.println(ls);
        //使用迭代器遍历
        Iterator<String> iter = ls.iterator();
        while (iter.hasNext()) {
            System.out.println("> " + iter.next());
        }
        //使用foreach遍历
        for (String s : ls) {
            System.out.println("-- " + s);
        }

    }
}
