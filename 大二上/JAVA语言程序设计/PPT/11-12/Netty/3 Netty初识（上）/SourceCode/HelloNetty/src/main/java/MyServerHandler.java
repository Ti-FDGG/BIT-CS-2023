import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

//ChannelHandler类负责处理与客户端之间的数据交换
//Sharable注解表示，这个类的实例是Singleton的，可以被多个ChannelHander流水线（pipeline）重用
//ChannelInboundHandlerAdapter是Netty内置的一个类，它实现了ChannelInboundHandler接口，
//为此接口中的方法提供了默认实现，我们可以选择需要的方法进行重写
@ChannelHandler.Sharable
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    //当服务端与客户端的数据通道建立时，Netty会回调此方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端连接：" + ctx.channel().remoteAddress());
    }
}
