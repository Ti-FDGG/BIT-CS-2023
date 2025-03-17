package daytime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class DateTimeClient {


     // 从Server端获取当前的时间
     // 只接收数据，不发送数据
    public static void main(String[] args) {
        String server = "127.0.0.1";
        int port = 8899;
        //Socket创建之后，自动发起连接
        try (Socket socket = new Socket(server, port)) {
            Reader reader = new InputStreamReader(socket.getInputStream(),
                    StandardCharsets.UTF_8);
            //从输入流中读取一行（因为Server端的数据是以“\r\n”结尾的
            BufferedReader bufferedReader = new BufferedReader(reader);
            String now = bufferedReader.readLine();
            if (now != null) {
                System.out.println(now);
            } else {
                System.out.println("未能成功地读入数据。");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
