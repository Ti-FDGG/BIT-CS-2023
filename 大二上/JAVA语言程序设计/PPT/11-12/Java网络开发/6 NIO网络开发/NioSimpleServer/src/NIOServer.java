import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws IOException {
        //用于接收客户端连接的Selector
        Selector serverSelector = Selector.open();
        //用于接收客户端发来消息的Selector
        Selector clientSelector = Selector.open();
        //当有客户端连接时，接收它，创建与客户端通讯的通道，注册到clientSelector上
        waitConnection(serverSelector, clientSelector);
        //通过clientSelector监听客户端发来的消息，并且显示在屏幕上
        receiveInfoFromClient(clientSelector);
    }

    //接收客户端响应
    private static void receiveInfoFromClient(Selector clientSelector) {
        Runnable runnable = () -> {
            try {
                while (true) {
                    if (clientSelector.select(10000) > 0) {
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        var keyInterator = set.iterator();
                        while (keyInterator.hasNext()) {
                            var key = keyInterator.next();
                            //只对“数据接收”事件感兴趣
                            if (key.isReadable()) {
                                try {
                                    //接收数据并输出
                                    receiveMessageAndOutput(key);
                                } finally {
                                    keyInterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
            }
        };
        new Thread(runnable).start();
    }

    private static void receiveMessageAndOutput(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        //分配数据绥冲区
        var byteBuffer = ByteBuffer.allocate(1024);
        clientChannel.read(byteBuffer);
        byteBuffer.flip();

        String clientAddress = String.valueOf(clientChannel.getRemoteAddress());
        //解码传入的消息并输出
        String message = StandardCharsets.UTF_8.newDecoder()
                .decode(byteBuffer).toString();
        System.out.println(clientAddress + "发来：" + message);
    }

    //等待客户端连接
    private static void waitConnection(Selector serverSelector, Selector clientSelector) {
        Runnable runnable = () -> {
            try {
                //创建用于监听的服务端通道
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                //设置为非阻塞模式
                listenerChannel.configureBlocking(false);
                //绑定8000端口，进行监听
                listenerChannel.socket().bind(new InetSocketAddress(8000));
                //监听通道向Selector注册，表明对“客户端连接（OP_ACCEPT）”事件感兴趣
                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
                System.out.println("服务端己启动，在8000端口监听");
                while (true) {
                    //处理客户端连接
                    handleConnectRequest(serverSelector, clientSelector);
                }
            } catch (Exception e) {
            }
        };
        new Thread(runnable).start();
    }

    //处理客户端连接请求
    private static void handleConnectRequest(Selector serverSelector, Selector clientSelector) throws IOException {
        //最长等待10秒，如果10秒内没有连接，继续下轮循环
        if (serverSelector.select(10000) > 0) {
            //获取当前事件列表
            Set<SelectionKey> set = serverSelector.selectedKeys();
            //遍历事件列表
            Iterator<SelectionKey> keyIterable = set.iterator();
            while (keyIterable.hasNext()) {
                SelectionKey key = keyIterable.next();
                //如果是连接事件
                if (key.isAcceptable()) {
                    try {
                        //创建与此客户端通讯的通道
                        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                        //设置为非阻塞模式
                        clientChannel.configureBlocking(false);
                        //通道向Selector注册，表明对“数据读取（OP_READ）”事件感兴趣
                        clientChannel.register(clientSelector, SelectionKey.OP_READ);
                    } finally {
                        //此事件己经处理完毕，可以从事件集合中移除
                        keyIterable.remove();
                    }
                }
            }
        }
    }
}
