import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {
    public static void main(String[] args)
            throws UnknownHostException {
        var address = InetAddress.getLocalHost();
        System.out.println("Name:" + address.getCanonicalHostName());
        System.out.println("IP:" + address.getHostAddress());
    }
}
