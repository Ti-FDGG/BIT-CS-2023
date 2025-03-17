import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    //监听端口
    private int DEFAULT_PORT = 8888;
    //客户端退出命令（即“下线”）
    private final String QUIT = "quit";
    //用于监听客户端连接的端口
    private ServerSocket serverSocket;
    //用于保存在线用户信息
    private Map<Integer, Writer> connectedClients;

    public ChatServer() {
        connectedClients = new HashMap<>();
    }

    //有客户端连接进来之后，将其加入到在线用户清单中，同时在控制台窗口输出一条信息
    public synchronized void addClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            connectedClients.put(port, writer);
            System.out.println("客户端[" + port + "]已连接到服务器");
        }
    }

    //用户下线
    public synchronized void removeClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            if (connectedClients.containsKey(port)) {
                connectedClients.get(port).close();
            }
            connectedClients.remove(port);
            System.out.println("客户端[" + port + "]已断开连接");
        }
    }

    //一个用户发来消息，Server转发消息给其他的在线用户
    public synchronized void forwardMessage(Socket socket, String fwdMsg)
            throws IOException {
        for (Integer id : connectedClients.keySet()) {
            if (!id.equals(socket.getPort())) {
                Writer writer = connectedClients.get(id);
                writer.write(fwdMsg);
                writer.flush();
            }
        }
    }

    //判断用户发来的消息是不是“下线”请求
    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    //关闭聊天服务
    public synchronized void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
                System.out.println("关闭serverSocket");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //启动聊天服务
    public void start() {
        try {
            // 绑定监听端口
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口：" + DEFAULT_PORT + "...");
            while (true) {
                // 等待客户端连接
                Socket socket = serverSocket.accept();
                // 创建ChatHandler线程
                new Thread(new ChatHandler(this, socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        //启动聊天服务
        server.start();
    }
}
