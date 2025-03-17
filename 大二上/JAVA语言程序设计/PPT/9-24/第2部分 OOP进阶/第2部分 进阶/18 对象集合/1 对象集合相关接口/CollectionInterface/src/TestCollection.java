import java.util.*;

public class TestCollection {
    public static void main(String[] args) {

        //ArrayList实现了Collection接口
        Collection arrayList = new ArrayList();

        addAndRemoveElements(arrayList);

        //判断是否包含指定字符串
        System.out.println("ArrayList集合是否包含“张三”字符串？" +
                arrayList.contains("张三"));


        arrayListAndHashSet(arrayList);

        useRetainAll();

        collectionToArray();

    }

    static void useRetainAll() {
        var list1 = new ArrayList<Integer>();
        //一次追加多个元素到ArrayList中
        list1.addAll(List.of(1, 2, 3, 4));
        var list = List.of(4, 5);
        //可以使用一个集合中的元素，创建并初始化另一个集合
        var list2 = new ArrayList<Integer>(list);
        list1.retainAll(list2);
        //输出：4
        System.out.println(list1);

    }

    private static void arrayListAndHashSet(Collection arrayList) {
        //HashSet也同样实现了Collection接口
        Collection<String> books = new HashSet<String>();
        books.add("book1");
        books.add("book2");
        arrayList.add("book1");
        System.out.println("ArrayList集合的元素：" + arrayList);

        //集合之间可以相互配合工作，因为它们实现了相同的集合接口
        System.out.println("ArrayList集合是否完全包含books集合？" +
                arrayList.containsAll(books));
        //用arrayList集合减去在books集合里的元素
        arrayList.removeAll(books);
        System.out.println("ArrayList集合的元素：" + arrayList);
    }

    private static void addAndRemoveElements(Collection arrayList) {
        //添加元素
        arrayList.add("张三");
        //虽然集合里不能放基本类型的值，但Java支持自动装箱
        arrayList.add(6);
        System.out.println("ArrayList包容的所有元素：" + arrayList);
        System.out.println("ArrayList集合的元素个数为:"
                + arrayList.size());
        //删除指定元素（注意，这里的参数不是ArrayList元素下标，而是元素本身）
        var result = arrayList.remove(6);
        if (result) {
            System.out.println("删除元素6成功！当前ArrayList集合的元素个数为:"
                    + arrayList.size());
            System.out.println("当前ArrayList包容的内容：" + arrayList);
        } else {
            System.out.println("删除失败");
        }

    }

    static void collectionToArray(){
        var stringList=List.of("one","two","three");
        var stringArr=stringList.toArray(String[]::new);
        //输出：[one, two, three]
        System.out.println(Arrays.toString(stringArr));
    }

}


