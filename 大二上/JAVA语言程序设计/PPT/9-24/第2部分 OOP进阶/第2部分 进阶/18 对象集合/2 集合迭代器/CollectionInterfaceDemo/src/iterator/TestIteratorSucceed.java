package iterator;

import java.util.*;


public class TestIteratorSucceed {
	public static void main(String[] args) {
		//创建一个集合
		Collection<String> books = new HashSet<>();
		books.add("One book");
		books.add("Two book");
		books.add("Three book");

		//获取books集合对应的迭代器
		Iterator<String> it = books.iterator();
		while(it.hasNext())	{
			String book = it.next();
			System.out.println(book);
			if (book.equals("One book")) {
				//使用Iterator迭代过程中，通过它来移除集合元素是安全的
				it.remove();
			}
		}
		System.out.println("移除元素之后："+books);
	}

}
