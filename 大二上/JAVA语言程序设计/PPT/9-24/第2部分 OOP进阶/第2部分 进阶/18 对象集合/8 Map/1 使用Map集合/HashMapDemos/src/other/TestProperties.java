package other;

import java.io.*;
import java.util.*;


public class TestProperties {
	public static void main(String[] args) throws Exception	{
		var props = new Properties();
		//向Properties中增加属性
		props.setProperty("username" , "zhang shan");
		props.setProperty("password" , "123456");
		//将Properties中的属性保存到a.ini文件中
		props.store(new FileOutputStream("a.ini") ,
				"comment line");
		//新建一个Properties对象
		var props2 = new Properties();
		//向Properties中增加属性
		props2.setProperty("gender" , "male");
		//将a.ini文件中的属性名-属性值追加到props2中
		props2.load(new FileInputStream("a.ini") );
		//输出：{password=123456, gender=male, username=zhang shan}
		System.out.println(props2);
	}
}
