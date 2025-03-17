import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class ChatClient {
    private static final String DEFAULT_SERVER_HOST = "127.0.0.1";
    private static final int DEFAULT_SERVER_PORT = 8888;
    private static final String QUIT = "quit";
    private static final int BUFFER = 1024;

    private String host;
    private int port;
    private SocketChannel client;

    private Selector selector;
    private Charset charset = StandardCharsets.UTF_8;

    public ChatClient() {
        this(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
    }

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    private void close(Closeable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void start() {
        try {
            //创建Socket通道
            client = SocketChannel.open();
            //配置为非阻塞模式
            client.configureBlocking(false);
            //创建Selector对象
            selector = Selector.open();
            //注册通道，声明对连接事件（OP_CONNECT）感兴趣
            client.register(selector, SelectionKey.OP_CONNECT);
            //按照预先设定好的IP地址和端口连接服务器
            client.connect(new InetSocketAddress(host, port));
            while (true) {
                selector.select();//等待服务器响应连接请求
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key : selectionKeys) {
                    handles(key);
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClosedSelectorException e) {
            // 用户正常退出
        } finally {
            close(selector);
        }
    }

    //处理通道事件
    private void handles(SelectionKey key) throws IOException {
        // CONNECT事件 - 连接就绪事件
        if (key.isConnectable()) {
            SocketChannel client = (SocketChannel) key.channel();
            if (client.isConnectionPending()) {
                client.finishConnect();
                // 处理用户的输入
                new Thread(new UserInputHandler(this)).start();
            }
            //注册通道，表明对读数据感兴趣
            client.register(selector, SelectionKey.OP_READ);
        }
        // READ事件 -  服务器转发消息
        else if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            String msg = receive(client);
            if (msg.isEmpty()) {
                // 服务器异常
                close(selector);
            } else {
                System.out.println(msg);
            }
        }
    }


    private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
    //发送消息
    public void send(String msg) throws IOException {
        if (msg.isEmpty()) {
            return;
        }
        wBuffer.clear();
        wBuffer.put(charset.encode(msg));
        wBuffer.flip();
        while (wBuffer.hasRemaining()) {
            client.write(wBuffer);
        }
        // 检查用户是否准备退出
        if (readyToQuit(msg)) {
            close(selector);
        }
    }

    private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);
    //接收消息
    private String receive(SocketChannel client) throws IOException {
        rBuffer.clear();
        while (client.read(rBuffer) > 0);
        rBuffer.flip();
        return String.valueOf(charset.decode(rBuffer));
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient("127.0.0.1", 7777);
        client.start();
    }
}
