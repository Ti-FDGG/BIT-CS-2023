package iterator;

import java.util.*;


public class TestForeach {
    public static void main(String[] args) {
        //创建一个集合
        Collection<String> books = new HashSet<String>();
        books.add("One book");
        books.add("Two book");
        books.add("Three book");
        for (var book : books) {
            System.out.println(book);
        }
        System.out.println(books);
        //使用Lambda表达式特性（方法引用）可以方便地遍历集合
        books.forEach(System.out::println);


    }


}

