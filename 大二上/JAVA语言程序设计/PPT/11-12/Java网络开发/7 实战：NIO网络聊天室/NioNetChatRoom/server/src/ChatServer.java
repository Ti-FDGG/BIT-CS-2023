import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class ChatServer {
    private static final int DEFAULT_PORT = 8888;
    private static final String QUIT = "quit";
    private static final int BUFFER = 1024;

    private ServerSocketChannel server;
    private Selector selector;
    private int port;

    public ChatServer() {
        this(DEFAULT_PORT);
    }

    public ChatServer(int port) {
        this.port = port;
    }

    private void start() {
        try {
            //开启服务端Socket通道
            server = ServerSocketChannel.open();
            //配置为非阻塞状态
            server.configureBlocking(false);
            //监听指定的端口
            server.socket().bind(new InetSocketAddress(port));
            //打开选择器
            selector = Selector.open();
            //将用于监听的通道注册到Slector，指明对Accept(即接收客户端连接请求）感兴趣
            server.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("启动服务器， 监听端口：" + port + "...");
            while (true) {
                //在此阻塞等待客户端的连接请求
                selector.select();
                //如果有连接请求，提取出相关的信息数据项（即SelectionKey）
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //依次处理信息数据项
                for (SelectionKey key : selectionKeys) {
                    // 处理数据项
                    handles(key);
                }
                //本轮数据项己经处理完毕，清空集合，继续循环，阻塞等待下次连接
                selectionKeys.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(selector);
        }
    }
    //事件处理代码
    private void handles(SelectionKey key) throws IOException {
        // ACCEPT事件 - 和客户端建立了连接
        if (key.isAcceptable()) {
            //接收用户连接请求
            handleUserConnection(key);
        }
        // READ事件 - 客户端发送了消息
        else if (key.isReadable()) {
            //接收用户消息，并转发给其他用户
            readAndForwardMessage(key);
        }
    }

    private void readAndForwardMessage(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        //从通道中接收信息
        String fwdMsg = receive(client);
        if (fwdMsg.isEmpty()) {
            // 如果消息没取出来，表明客户端异常，以后就不再需要接收信息了
            key.cancel();
            //立即从此轮处理中返回
            selector.wakeup();
        } else {
            //消息取出来了
            System.out.println(getClientName(client) + ":" + fwdMsg);
            //向其它在线用户转发消息
            forwardMessage(client, fwdMsg);
            // 检查用户是否退出
            if (readyToQuit(fwdMsg)) {
                key.cancel();
                selector.wakeup();
                System.out.println(getClientName(client) + "已断开");
            }
        }
    }

    //接收用户连接请求
    private void handleUserConnection(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        //获取用于与客户端进行数据传播的通道
        SocketChannel client = server.accept();
        //配置为非阻塞状态
        client.configureBlocking(false);
        //向选择器注册，表明此通道将用于接收数据，对OP_READ事件感兴趣
        client.register(selector, SelectionKey.OP_READ);
        System.out.println(getClientName(client) + "已连接");
    }

    //用于转发消息使用的ByteBuffer
    private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
    //使用UTF8解码与编码消息，以支持中文
    private Charset charset = StandardCharsets.UTF_8;
    //转发消息
    private void forwardMessage(SocketChannel client, String fwdMsg) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel connectedClient = key.channel();
            //排除用于监听连接的Server通道
            if (connectedClient instanceof ServerSocketChannel) {
                continue;
            }
            //消息只转发给其他用户（排除发消息的那个用户）
            if (key.isValid() && !client.equals(connectedClient)) {
                wBuffer.clear();
                wBuffer.put(charset.encode(getClientName(client) + ":" + fwdMsg));
                wBuffer.flip();
                while (wBuffer.hasRemaining()) {
                    ((SocketChannel) connectedClient).write(wBuffer);
                }
            }
        }
    }

    //用于接收消息的缓冲区
    private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);

    //消息的接收
    private String receive(SocketChannel client) throws IOException {
        rBuffer.clear();
        while (client.read(rBuffer) > 0) ;
        rBuffer.flip();
        return String.valueOf(charset.decode(rBuffer));
    }

    //获取用户信息
    private String getClientName(SocketChannel client) {
        return "客户端[" + client.socket().getPort() + "]";
    }

    //检查用户是否发来了“quit”消息
    private boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    //释放资源
    private void close(Closeable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(7777);
        chatServer.start();
    }
}
