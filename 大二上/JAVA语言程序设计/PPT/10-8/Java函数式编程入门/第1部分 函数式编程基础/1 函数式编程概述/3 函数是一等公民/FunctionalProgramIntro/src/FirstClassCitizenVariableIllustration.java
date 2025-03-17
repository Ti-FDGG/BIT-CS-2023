import java.util.function.BiFunction;

public class FirstClassCitizenVariableIllustration {

	private static String concatStrings(String a,String b) {
		return a+b;
	}
	private String concatStrings2(String a,String b) {
		return a+b;
	}
	public static void main(String[] args) {

		//将一个Lambda表达式赋给一个函数式接口类型的变量
		BiFunction<String, String, String> concatFunction=(s,t)->{
			return s+t;
		};
		//直接调用这个变量
		System.out.println(concatFunction.apply("Hello ", "World 1"));

		//让变量引用一个满足条件的静态方法
		concatFunction=FirstClassCitizenVariableIllustration::concatStrings;
		System.out.println(concatFunction.apply("Hello ", "World 2"));

		//让变量引用一个满足条件的实例方法
		FirstClassCitizenVariableIllustration obj=new FirstClassCitizenVariableIllustration();
		concatFunction=obj::concatStrings2;
		System.out.println(concatFunction.apply("Hello ", "World 3"));

	}

}

