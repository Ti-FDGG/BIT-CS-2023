package iterator;

import java.util.*;

public class TestIteratorError
{
	public static void main(String[] args) {

		//创建一个集合
		Collection<String> books = new HashSet<>();
		books.add("One book");
		books.add("Two book");
		books.add("Three book");
		System.out.println("集合中包容的所有元素："+books);

//		//获取books集合对应的迭代器
//		Iterator<String> it = books.iterator();
//		//使用Iterator迭代过程中，不可修改集合元素！
//		while(it.hasNext()) {
//			String book = it.next();
//			System.out.println(book);
//			if (book.equals("One book")) {
//				System.out.println("在遍历集合元素时，删除One book！");
//				books.remove(book);
//			}
//		}

		//灵异现象——如果删除"Two book",本示例不会引发异常，为什么呢？
//		while(it.hasNext()) {
//			String book = it.next();
//			System.out.println(book);
//			if (book.equals("Two book")) {
//				System.out.println("在遍历集合元素时，删除Two book！");
//				//这句现在不引发异常了！
//				books.remove(book);
//			}
//		}

		//使用foreach循环，在里面尝试删除元素
//		for (String book : books) {
//			if (book.equals("One book")){
//				books.remove(book);
//			}
//		}


		System.out.println("移除元素之后："+books);
	}
}
