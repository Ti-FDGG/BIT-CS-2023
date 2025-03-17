import java.io.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

public class RedirectIO {
    public static void main(String[] args) throws IOException {
//        redirectIn();
//        redirectOut();
        redirectErr();
    }

    private static void redirectIn() {
        try (var fis = new FileInputStream("./src/RedirectIO.java")) {
            //将标准输入重定向到fis输入流
            System.setIn(fis);
            //使用System.in创建Scanner对象，用于获取标准输入
            Scanner sc = new Scanner(System.in);
            //增加下面一行将只把回车作为分隔符
            sc.useDelimiter("\n");
            //判断是否还有下一个输入项
            while (sc.hasNext()) {
                //输出输入项
                System.out.println("从文件提取：" + sc.next());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void redirectOut() {
        try (PrintStream ps = new PrintStream(
                new FileOutputStream("out.txt"))) {
            //将标准输出重定向到ps输出流
            System.setOut(ps);
            //向标准输出输出一个字符串
            System.out.println("普通字符串");
            //向标准输出输出一个对象
            System.out.println(new Date());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void redirectErr() throws FileNotFoundException {
        String LOGFILENAME = "runInfo.log";
        try (var printStream = new PrintStream(new FileOutputStream(LOGFILENAME))) {
            System.setErr(printStream);
            System.out.println("运行时信息被写入文件： " + LOGFILENAME);
            System.err.println("----------本程序的运行信息日志-------------");
            System.err.println("错误发生于：" + LocalTime.now());
        };
    }
}