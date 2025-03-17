import java.util.ArrayList;
import java.util.LinkedList;

public class TestPerformance {
    public static void main(String[] args) {
        //创建一个字符串数组
        String[] tst1 = new String[900000];
        //动态初始化数组元素
        for (int i = 0; i < 900000; i++) {
            tst1[i] = String.valueOf(i);
        }
        var arrayList = new ArrayList<String>();
        var linkedList = new LinkedList<String>();

        //迭代访问ArrayList集合的所有元素，并输出迭代时间
        long start = System.nanoTime();
        //将所有数组元素加入ArrayList集合中
        for (int i = 0; i < 900000; i++) {
            arrayList.add(tst1[i]);
        }
        for (String s : arrayList) {
        }
        System.out.println("迭代ArrayList集合元素的时间:" + (System.nanoTime() - start));

        //迭代访问LinkedList集合的所有元素，并输出迭代时间
        start = System.nanoTime();
        //将所有数组元素加入LinkedList集合中
        for (int i = 0; i < 900000; i++) {
            linkedList.add(tst1[i]);
        }
        for (var elem : linkedList) {
        }

        System.out.println("迭代LinkedList集合元素的时间:" + (System.nanoTime() - start));
    }
}
