import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.awt.image.ImageConsumer;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //一个用于保存在线用户Channel的集合
    private static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端发来的消息
        String content = msg.text();
        System.out.println("客户端发来：" + content);
        //群发消息
        for (var channel : clients) {
            channel.writeAndFlush(new TextWebSocketFrame(LocalTime.now() + ":" + content));
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        clients.remove(ctx.channel());
        System.out.println("客户端" + ctx.channel().id().asLongText() + "断开。");
    }
}
