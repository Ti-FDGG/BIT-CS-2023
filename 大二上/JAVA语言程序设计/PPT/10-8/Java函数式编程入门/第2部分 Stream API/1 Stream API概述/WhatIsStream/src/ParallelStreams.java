

import java.util.ArrayList;
import java.util.List;

public class ParallelStreams {
	public static void main(String[] args){
		System.out.println("创建字符串集合");
		List<String> strings = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			strings.add("Item " + i);
		}
		//乱序显示集合中的元素
		System.out.println("集合中包容以下元素：");
		strings.parallelStream()
			.forEach(System.out::println);
	}
}