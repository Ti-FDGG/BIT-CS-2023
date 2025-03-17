import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 展示如何使用URLConnection获取HTTP响应的Header和Body
 */
public class URLConnectionTest {
 public static void main(String[] args) {

        BufferedReader bufferedReader = null;
        try {
            URL url = new URL("http://www.baidu.com/");
            URLConnection urlConnection = url.openConnection();
            //获取Server返回的HTTP响应首部
            Map<String, List<String>> headers = urlConnection.getHeaderFields();
            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
            for (Map.Entry<String, List<String>> entry : entrySet) {
                String headerName = entry.getKey();
                System.out.println("Header Name:" + headerName);
                List<String> headerValues = entry.getValue();
                for (String value : headerValues) {
                    System.out.print("Header value:" + value);
                }
                System.out.println();
            }
            //获取HTTP响应的Body
            InputStream inputStream = urlConnection.getInputStream();
            bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
