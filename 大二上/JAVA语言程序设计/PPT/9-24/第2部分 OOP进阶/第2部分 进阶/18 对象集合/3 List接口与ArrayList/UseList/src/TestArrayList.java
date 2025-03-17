
import java.util.*;
public class TestArrayList {

    public static void main(String[] args) {

        List<String> books = new ArrayList<>();
        //向books集合中添加三个元素
        books.add("One book");
        books.add("Two book");
        books.add("Three book");
        System.out.println(books);
        //将新字符串对象插入在第二个位置
        books.add(1, "new book");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
        //删除第三个元素
        books.remove(2);
        System.out.println(books);
        //判断指定元素在List集合中位置：输出1，表明位于第二位
        System.out.println(books.indexOf("new book"));
        //将第二个元素替换成新的字符串对象
        books.set(1, "new book2");
        System.out.println(books);
        //将books集合的第二个元素（包括）到第三个元素（不包括）截取子集合
        var subList=books.subList(1,2);
        //输出：[new book2]
        System.out.println(books.subList(1, 2));
        //修改subList的值
        subList.set(0,"新改的值");
        //可以看到原始集合值也改了
        //输出：[One book, 新改的值, Three book]
        System.out.println(books);

    }
}
