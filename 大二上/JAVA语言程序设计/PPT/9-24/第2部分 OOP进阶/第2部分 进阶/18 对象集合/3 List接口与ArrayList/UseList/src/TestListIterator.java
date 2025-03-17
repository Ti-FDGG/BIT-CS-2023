
import java.util.*;


public class TestListIterator {
	public static void main(String[] args)	{
		//创建示例集合
		String[] books = {
				"One book",
				"Two book"
		};
		List<String> bookList = new ArrayList<>();
		Collections.addAll(bookList, books);

		//获取迭代器对象
		ListIterator<String> lit = bookList.listIterator();
		System.out.println("先正向迭代");
		while (lit.hasNext())		{
			//可以在迭代过程中添加新元素
			lit.add("|");
			System.out.println(lit.next());
		}
		System.out.println("再反向迭代");
		while(lit.hasPrevious())		{
			System.out.println(lit.previous());
		}

		printReversedList(bookList);
	}

	//一个比较通用的反向迭代方法，适用于所有的List
	static <E> void printReversedList(List<E> list){
		var iterator=list.listIterator(list.size());
		System.out.println("\n---List内容---\n");
		while(iterator.hasPrevious()){
			System.out.print(iterator.previous());
		}
	}
}
