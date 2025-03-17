import model.CommonClass;

import java.util.Random;
import java.util.TreeSet;

public class CommonClassAndTreeSet {
    public static void main(String[] args) {

        //指定TreeSet使用一个Comparator对象
        var treeSet = new TreeSet<CommonClass>(CommonClass.BY_NUMS);
        Random ran = new Random();
        //向集合中加入3个元素
        for (int i = 0; i < 3; i++) {
            var obj = new CommonClass();
            int ranValue = ran.nextInt(100);
            obj.setNum(ranValue);
            obj.setInfo("info " + ranValue);
            treeSet.add(obj);
        }
        //某次程序的输出：
        // [homework.MyClass{nums=35, info='info 35'},
        //  homework.MyClass{nums=46, info='info 46'},
        //  homework.MyClass{nums=75, info='info 75'}]
        System.out.println(treeSet);
    }
}
