import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

public class UsePrintStreamSaveText {
    public static void main(String[] args) throws FileNotFoundException {
        basedOnOutputStream();
//        directUse();
    }

    //基于输出流而构建
    private static void basedOnOutputStream() {
        PrintStream ps = null;
        //创建一个文件输出流：FileOutputStream
        try(FileOutputStream fos = new FileOutputStream("poem.txt")) {
            //以PrintStream来包装FileOutputStream输出流
            ps = new PrintStream(fos);
            //使用PrintStream执行输出
            ps.println("打油诗一首");
            ps.println("远看泰山黑乎乎");
            ps.println("上边细来下边粗");
            ps.println("有朝一日倒过来");
            ps.println("下边细来上边粗");
        } catch (IOException ioe) {
            ioe.printStackTrace(ps);
        }
        System.out.println("数据己经写入poem.txt");
    }

    private static void directUse() throws FileNotFoundException {
        //直接实例化PrintStream对象
        try (var printStream = new PrintStream("poem.txt")) {
            printStream.println("打油诗一首");
            printStream.println("远看泰山黑乎乎");
            printStream.println("上边细来下边粗");
            printStream.println("有朝一日倒过来");
            printStream.println("下边细来上边粗");
        };
        System.out.println("数据己经写入poem.txt");
    }
}
