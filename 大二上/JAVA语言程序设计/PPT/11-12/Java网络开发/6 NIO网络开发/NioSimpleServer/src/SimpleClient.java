import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Date;

public class SimpleClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                String message = null;
                while (true) {
                    message = LocalTime.now() + " 你好，Server!";
                    socket.getOutputStream().write(
                            message.getBytes(StandardCharsets.UTF_8)
                    );
                    System.out.println("发送消息：" + message);
                    Thread.sleep(2000);
                }
            } catch (IOException e) {

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
