import java.util.LinkedHashSet;
import java.util.List;

public class UseLinkedHashSet {
    public static void main(String[] args) {
        //LinkedHashSet内部使用双向链表保存数据
        var linkedHashSet = new LinkedHashSet<Integer>();
        linkedHashSet.addAll(List.of(5, 2, 31, 49, 66));
        linkedHashSet.add(100);
        //将按照加入的顺序输出：5 | 2 | 31 | 49 | 66 | 100 |
        for (var elem : linkedHashSet) {
            System.out.print(elem + " | ");
        }
        System.out.println();
        //移除中间的一个元素
        linkedHashSet.remove(31);
        //再输出一次：5 | 2 | 49 | 66 | 100 |
        for (var elem : linkedHashSet) {
            System.out.print(elem + " | ");
        }
    }
}
