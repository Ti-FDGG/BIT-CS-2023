import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.charset.Charset;

public class TestPing {
    public static void main(String[] args) {
        try {
            String hostAddress = "www.baidu.com";
            InetAddress host = InetAddress.getByName(hostAddress);

            Process p = Runtime.getRuntime().exec("ping " + hostAddress);
            var inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream(),
                            Charset.forName("GBK")));
            String commandOutput = "";
            while ((commandOutput = inputStream.readLine()) != null) {
                System.out.println(commandOutput);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
