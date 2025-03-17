import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
public class DownloadWebFile {

    /**
     * 下载百度首页
     */
    public static void main(String[] args) {
        BufferedReader bufferedReader=null;
        try{
            //创建URL对象
            URL url = new URL("http://www.bit.edu.cn/");
            //获取输入流
            InputStream inputStream = url.openStream();
            //从流中读取数据，注意其编码
            bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream,
                            Charset.forName("utf-8")));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(bufferedReader!=null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}