import java.util.*;
public class UseTreeSetCommon {
    public static void main(String[] args) {

        //向TreeSet中添加四个Integer对象
        var nums = new TreeSet<Integer>(List.of(5, 2, 10, -9));
        //输出集合元素，看到集合元素已经处于排序状态
        System.out.println("集合元素:" + nums);
        //输出集合里的第一个元素
        System.out.println("集合里的第一个元素:" + nums.first());
        //输出集合里的最后一个元素
        System.out.println("集合里的最后一个元素:" + nums.last());
        //可以输出指定元素的“前一个”和“后一个”
        //练习：如果lower()方法指定的元素在TreeSet中不存在，会出现什么情况？
        //编程试一试！
        System.out.println("10之前的那个元素是：" + nums.lower(10));
        System.out.println("10之后的那个元素是：" + nums.higher(10));
        System.out.println("floor(6)=" + nums.floor(6));
        System.out.println("ceiling(6)=" + nums.ceiling(6));
        //返回小于4的子集，不包含4
        System.out.println("返回小于4的子集，不包含4:" + nums.headSet(4));
        //返回大于5的子集，如果Set中包含5，子集中还包含5
        System.out.println("返回大于5的子集:" + nums.tailSet(5));
        //返回大于等于-3，小于4的子集。
        System.out.println("返回大于等于-3，小于4的子集:" + nums.subSet(-3, 4));
    }
}
