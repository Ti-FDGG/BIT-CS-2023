import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CompileAndRun {
    public static void main(String[] args)
            throws IOException, InterruptedException {
        String workingDir = System.getProperty("java.class.path");
        System.out.println("当前工作目录：" + workingDir);
        //运行时调用javac编译器，即时编译Java文件，生成字节码文件：Hello.class
        compileJavaFile(workingDir, "Hello.java");
        //运行时调用java命令，运行Hello.class
        runJavaClassFile(workingDir, "Hello");
    }


    private static void runJavaClassFile(String workingDir, String javaClassFileName) {
        ProcessBuilder processBuilder =
                new ProcessBuilder("java", javaClassFileName);
        try {
            processBuilder.directory(new File(workingDir));
            Process process = processBuilder.start();
            //捕获进程的输出，输出在控制台窗口中
            //为了在Windows的命令提示符窗口中正确显示汉字，按GBK方式解码字符串
            var br = new BufferedReader(
                    new InputStreamReader(process.getInputStream(),
                            Charset.forName("GBK")));
            //在命令提示符窗口显示示例程序运行时的输出
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //动态编译Java文件
    private static void compileJavaFile(String workingDir, String javaFileName)
            throws InterruptedException, IOException {
        //由于Hello.java中包容有中文字符，所以，需要在编译时指定字符编码
        var compiler = new ProcessBuilder("javac", "-encoding", "UTF-8",
                "-d", workingDir, workingDir + "\\" + javaFileName);
        //指定编译器工作路径
        compiler.directory(new File(workingDir));
        //等待编译完成
        compiler.start().waitFor();
        System.out.println("Compiled finished");
    }
}
