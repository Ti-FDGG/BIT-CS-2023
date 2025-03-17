import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 展示Socket的基本用法
 *
 * @author JinXuLiang
 */
public class LearnSocket {
    public static void main(String[] args) {
//        LearnSocketAddress();
//        LowPortScann();
        downloadWebPage();
   }

    //了解本机与远程IP地址及端口
    private static void LearnSocketAddress() {
        try (Socket socket = new Socket("baidu.com", 80)) {
            System.out.println("远程的IP终结点：");
            System.out.println(socket.getInetAddress());
            System.out.println(socket.getPort());

            System.out.println("\n本机终结点");
            System.out.println(socket.getLocalAddress());
            System.out.println(socket.getLocalPort());

            System.out.println("\nSocket重写了toString()方法");
            System.out.println(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 扫描[0,1023]中有哪些端口是打开的。
    private static void LowPortScann() {
        String host = "127.0.0.1";
        for (int i = 0; i < 1024; i++) {
            System.out.println("检查端口：" + i);
            try (Socket socket = new Socket(host, i)) {
                System.out.println(host + "打开了端口：" + i);
            } catch (Exception e) {
            }
        }
    }

    //依据HTTP协议的约定，基于Socket，直接地从网上下载网页
    private static void downloadWebPage() {
        String host = "www.baidu.com";
        try (Socket socket = new Socket(host, 80)) {
            boolean autoflush = true;
            //因为是需要向Server端发送HTTP命令，它们是文本型的，并且以换行结尾，
            //所以用PrintWriter比较方便
            //当然，也可以用OutputStreamWriter，但这时需要人工地加上换行符
            PrintWriter out = new PrintWriter(socket.getOutputStream(),
                    autoflush);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), StandardCharsets.UTF_8));
            // send an HTTP request to the web server
            out.println("GET / HTTP/1.1");
            out.println("Host: " + host + ":80");
            out.println("Connection: Close");
            out.println();
            //读取Server端发回的数据
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
