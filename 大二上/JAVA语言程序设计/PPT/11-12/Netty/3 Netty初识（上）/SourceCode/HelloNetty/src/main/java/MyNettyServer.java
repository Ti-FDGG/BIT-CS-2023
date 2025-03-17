import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyNettyServer {
    private final int port = 9000;
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerHandler());
            ChannelFuture channelFuture = boot.bind(port).sync().addListener(nettyFuture -> {
                if (nettyFuture.isSuccess()) {
                    System.out.println(port + "端口绑定成功。");
                } else {
                    System.out.println(port + "端口绑定成功失败。");
                }
            });
            //如果服务端通道被关闭，这里阻塞等待其完成
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            bossGroup.shutdownGracefully().addListener(future -> {
                System.out.println("bossGroup关闭。");
            });
            workerGroup.shutdownGracefully().addListener(future -> {
                System.out.println("workerGroup关闭。");
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new MyNettyServer().run();
    }
}
