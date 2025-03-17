import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args)
            throws Exception {
        useInetAddress();
        System.out.println();
        useInetSocketAddress();
    }

    private static InetAddress useInetAddress() throws IOException {
        // 根据主机名来获取对应的InetAddress实例
        InetAddress ip = InetAddress.getByName("www.jinxuliang.com");
        // 判断是否可达
        System.out.println("教学网站是否可达:" + ip.isReachable(2000));
        // 获取该InetAddress实例的IP字符串
        System.out.println(ip.getHostAddress());
        // 根据原始IP地址来获取对应的InetAddress实例
        InetAddress local = InetAddress.getByAddress(
                new byte[]{127, 0, 0, 1});
        System.out.println("本机是否可达:" + local.isReachable(5000));
        // 获取该InetAddress实例对应的全限定域名
        System.out.println(local.getCanonicalHostName());
        return ip;
    }

    private static void useInetSocketAddress() throws UnknownHostException {
        InetAddress ip = InetAddress.getByName("www.jinxuliang.com");
        var socketAddress = new InetSocketAddress(ip, 9999);
        //www.jinxuliang.com/182.92.1.231:9999
        System.out.println(socketAddress);
        //www.jinxuliang.com
        System.out.println(socketAddress.getHostName());
        //www.jinxuliang.com/182.92.1.231
        System.out.println(socketAddress.getAddress());
        //9999
        System.out.println(socketAddress.getPort());
        //www.jinxuliang.com
        System.out.println(socketAddress.getHostString());
        //false
        System.out.println(socketAddress.isUnresolved());
    }
}

