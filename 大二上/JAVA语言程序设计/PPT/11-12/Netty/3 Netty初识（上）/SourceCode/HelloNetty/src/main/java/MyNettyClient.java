import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class MyNettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new MyClientHandler());
        //连接服务器
        var resultFuture = bootstrap.connect("127.0.0.1", 9000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功。");
            } else {
                System.out.println("连接失败。");
            }
        });
        //通道关闭后，关闭线程池
        resultFuture.channel().closeFuture().addListener(future -> {
            workerGroup.shutdownGracefully();
            System.out.println("程序已经退出。");
        });
    }
}
