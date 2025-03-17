import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

//使用BufferedReader，可以从Console中读取输入
public class ReadFromConsole {
    public static void main(String[] args) throws IOException {
        System.out.println("可以不断地输入字符串，要退出，输入q");
        try (var consoleReader = new BufferedReader(
                new InputStreamReader(System.in, Charset.forName("GBK"))
        )) {
            String line = null;
            //不断地从控制台读取用户输入
            while ((line = consoleReader.readLine()) != null) {
                if (line.toLowerCase().equals("q")) {
                    System.out.println("程序退出");
                    break;
                }
                if (line.trim().length() == 0) {
                    System.out.println("你输入了不可打印的字签");
                } else {
                    System.out.println("你输入了：" + line);
                }
            }
        }
    }
}
