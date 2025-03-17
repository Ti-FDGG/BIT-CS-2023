import java.io.*;
import java.net.Socket;

public class ChatClient {
    //服务器IP地址
    private final String DEFAULT_SERVER_HOST = "127.0.0.1";
    //服务器监听端口
    private final int DEFAULT_SERVER_PORT = 8888;
    //”下线“命令
    private final String QUIT = "quit";
    private Socket socket;
    //接收消息
    private BufferedReader reader;
    //发送消息
    private BufferedWriter writer;

    // 发送消息给服务器
    public void send(String msg) throws IOException {
        if (!socket.isOutputShutdown()) {
            writer.write(msg + "\n");
            writer.flush();
        }
    }

    // 从服务器接收消息
    public String receive() throws IOException {
        String msg = null;
        if (!socket.isInputShutdown()) {
            msg = reader.readLine();
        }
        return msg;
    }

    // 检查用户是否准备退出
    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    // 关闭Socket并退出
    public void close() {
        if (writer != null) {
            try {
                System.out.println("关闭socket");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        try {
            // 创建socket
            socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
            // 创建IO流
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );

            // 处理用户的输入
            new Thread(new UserInputHandler(this)).start();
            // 读取服务器转发的消息
            String msg = null;
            while ((msg = receive()) != null) {
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        chatClient.start();
    }
}
