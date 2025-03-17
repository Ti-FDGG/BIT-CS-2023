import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckedExceptionDemo {
    public static void main(String[] args)  {
        try {
            var buf = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.print("请输入整数: ");
            //readLine()方法声明会抛出IOException
            //parseInt()方法声明会抛出NumberFormatException
            int input = Integer.parseInt(buf.readLine());
            System.out.println("input x 10 = " + (input*10));
        }
        //以下异常处理语句块是必须的，否则无法通过编译
        //因为IOException的基类是Exception
        catch(IOException e) {
            System.out.println("I/O错误");
        }
        //以下异常处理语句块可以省略，不影响编译，
        //因为NumberFormatException的基类是RuntimeException
        //但可能会在运行时出错，因为用户可能会输入非法的数字
        catch(NumberFormatException e) {
            System.out.println("输入必须为整数");
        }
    }
}