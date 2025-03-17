import java.util.ArrayList;
import java.util.List;

public class ForEachExample {
	public static void main(String[] args) {
		var strings = new ArrayList<String>();
		strings.add("AAA");
		strings.add("bbb");
		strings.add("CCC");
		strings.add("ddd");
		strings.add("EEE");

		System.out.println("使用传统方式访问集合");
		for(String str:strings){
			System.out.println(str);
		}
		System.out.println("使用Lambda表达式访问集合");
		strings.forEach(str -> {
			System.out.println(str);
		});

		System.out.println("更简单的方式，使用方法引用");
		strings.forEach(System.out::println);
	}
}

