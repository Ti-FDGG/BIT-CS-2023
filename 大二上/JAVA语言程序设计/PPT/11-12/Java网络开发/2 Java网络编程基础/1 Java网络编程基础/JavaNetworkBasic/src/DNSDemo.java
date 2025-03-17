import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSDemo {
    public static void main(String[] args) {
        System.out.println("输入域名：");
        var input = new BufferedReader(new InputStreamReader(System.in));
        input.lines().forEach(line -> {
            try {
                var address = InetAddress.getByName(line);
                System.out.println("IP:" + address.getHostAddress());
            } catch (UnknownHostException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}

