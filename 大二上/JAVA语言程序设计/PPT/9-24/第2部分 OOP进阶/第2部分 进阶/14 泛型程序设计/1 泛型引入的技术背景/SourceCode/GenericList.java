
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GenericList {
    public static void main(String[] args) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //创建一个只想保存字符串的List集合
        List<String> strList = new ArrayList<String>();
        strList.add("One string");
        strList.add("Two string");
        strList.add("Three string");
        //下面代码将引起编译错误
        //strList.add(5);
        //但使用反射可以绕开编译器的语法检查
        //strList.getClass().getMethod("add", Object.class).invoke(strList, 5);
        for (int i = 0; i < strList.size(); i++) {
            //下面代码无需强制类型转换
            String str = strList.get(i);
            System.out.println(strList.get(i));
        }

    }
}