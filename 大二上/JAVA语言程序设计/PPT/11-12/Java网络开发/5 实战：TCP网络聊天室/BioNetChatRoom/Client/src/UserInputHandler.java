import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

//从键盘接收用户输入，然后将消息发送给Server
public class UserInputHandler implements Runnable {
    private ChatClient chatClient;
    public UserInputHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        try {
            // 等待用户输入消息
            BufferedReader consoleReader =
                    new BufferedReader(new InputStreamReader(System.in, Charset.forName("GBK")));
            while (true) {
                String input = consoleReader.readLine();
                // 向服务器发送消息
                chatClient.send(input);
                // 检查用户是否准备退出
                if (chatClient.readyToQuit(input)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
