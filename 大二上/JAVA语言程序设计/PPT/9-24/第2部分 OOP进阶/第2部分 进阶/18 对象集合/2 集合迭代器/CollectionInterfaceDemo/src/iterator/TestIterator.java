package iterator;

import java.util.*;

public class TestIterator {
    public static void main(String[] args) {
        //创建一个集合
        Collection<String> books = new HashSet<>();
        books.add("One book");
        books.add("Two book");
        books.add("Three book");
        //获取books集合对应的迭代器
        Iterator<String> it = books.iterator();
        //使用迭代器遍历集合
        while (it.hasNext()) {
            String book = (String) it.next();
            System.out.println(book);
        }
        System.out.println("集合中的元素为：" + books);
        //在迭代的过程中同时加工每个元素
        it.forEachRemaining(book->{
            System.out.println(book.toUpperCase());
        } );
    }
}
