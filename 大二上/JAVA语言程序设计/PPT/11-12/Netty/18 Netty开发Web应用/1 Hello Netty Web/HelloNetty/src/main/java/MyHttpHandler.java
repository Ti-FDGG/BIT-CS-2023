import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.time.LocalTime;
import java.util.Date;

public class MyHttpHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //如果收到的消息对象（从前一个Handler传来的）是一个请求对象
        if (msg instanceof HttpRequest request) {
            String uri = request.uri();
            System.out.println("收到的HTTP请求的URL:" + uri);
            String reponseText = "";
            if (uri.equals("/")) {
                reponseText = "<h3>你好！来自Netty Server的问候！</h3>";
            } else if (uri.equals("/now")) {
                reponseText = "当前时间：" + LocalTime.now();
            }
            ByteBuf content = Unpooled.copiedBuffer(reponseText, CharsetUtil.UTF_8);
            //生成HTTP响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            //设置必须的HTTP Header值
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //设置HTTP响应的body值
            response.content().writeBytes(content);
            //将响应发回给客户端
            ctx.writeAndFlush(response);
        }
    }
}
