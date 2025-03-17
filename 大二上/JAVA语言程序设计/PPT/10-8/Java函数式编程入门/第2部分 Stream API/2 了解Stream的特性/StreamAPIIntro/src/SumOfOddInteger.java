import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class SumOfOddInteger {

	public static void main(String[] args) {
		//seperateForm();
		compoundForm();
	}

	//以分步的方式展示Stream API如何完成复杂的数据处理任务
	private static void seperateForm() {
		// 生成一个包容[1,5]区间所有整数的集合
		List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5);
		// 将集合转变为流
		Stream<Integer> numbersStream = numbersList.stream();
		// 过滤提取出奇数
		Stream<Integer> oddNumbersStream= numbersStream.filter(n -> n % 2 == 1);
		// 求奇数的平方值（映射/转换）
		Stream<Integer> squaredNumbersStream = oddNumbersStream.map(n -> n * n);
		// 累加求和（归约）
		int sum = squaredNumbersStream.reduce(0, (n1, n2) -> n1 + n2);
		// 使用方法引用特性，可以改写为：
		//int sum = squaredNumbersStream.reduce(0, Integer::sum);
		// 输出处理结果
		System.out.println(sum);
	}

	//以级联的方式编程
	private static void compoundForm(){
		// 生成一个包容5个整数的集合
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		// 计算集合中所有奇数平方值的总和
		int sum = numbers.stream()
				.peek(num-> System.out.println("\nfilter之前，处理"+num))
				.filter(n -> n %2 ==1)
				.peek(num-> System.out.println("filter之后,map之前，过滤出"+num))
				.map(n -> n * n)
				.peek(num-> System.out.println("map之后，reduce之前，得到平方值："+num))
				.reduce(0, Integer::sum);
		System.out.println("\n数据处理结束，其结果为："+sum);
	}

}
