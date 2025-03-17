package iterator;

import java.util.*;

public class TestListIterator {
    public static void main(String[] args) {
        //创建一个集合
        ArrayList<String> books = createStringList();

//        visitListFromBeginToEnd(books);
//
//        visitListFromEndToBegin(books);

        iteratorAreNotSame();

//        modifyListWhenVisit(books);

    }

    //遍历的同时修改集合
    private static void modifyListWhenVisit(ArrayList<String> books) {
        ListIterator<String> iterator;
        iterator = books.listIterator();
        int index = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            //要先调用next()方法，之后才能更改它的元素
            index++;
            iterator.set(String.valueOf(index));
        }
        //输出修改后的结果
        books.forEach(System.out::println);
    }

    //反向遍历
    private static void visitListFromEndToBegin(ArrayList<String> books) {
        ListIterator<String> iterator = books.listIterator(books.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    //正向遍历
    private static void visitListFromBeginToEnd(ArrayList<String> books) {
        ListIterator<String> iterator = books.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //创建示例集合
    private static ArrayList<String> createStringList() {
        ArrayList<String> books = new ArrayList<>();
        books.add("One book");
        books.add("Two book");
        books.add("Three book");
        return books;
    }

    //同一个集合，返回的Iterator是独立的，互不影响。
    private static void iteratorAreNotSame() {
        var nums = List.of(1, 2, 3, 4, 5);
        Random random = new Random();
        Runnable runnable = () -> {
            var iterator = nums.listIterator();
            while (iterator.hasNext()) {
                try {
                    //随机休眠若干时间
                    Thread.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +
                        ":" + iterator.next());
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
