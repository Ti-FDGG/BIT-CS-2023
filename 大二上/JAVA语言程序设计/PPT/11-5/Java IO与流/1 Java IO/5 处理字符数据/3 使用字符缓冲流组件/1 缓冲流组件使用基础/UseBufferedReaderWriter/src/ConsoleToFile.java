
import java.io.*; 
 
public class ConsoleToFile {
    public static void main(String[] args) throws IOException {
        	System.out.println("输入文本，键入quit结束");
           // 缓冲System.in输入流
            var bufReader = new BufferedReader(
                      new InputStreamReader(System.in)); 
           // 缓冲FileWriter字符输出流
            var bufWriter =  new BufferedWriter(
                    new FileWriter("Hello.txt"));
            String input = null;
           // 每读一行进行一次写入动作
            while(!(input =  bufReader.readLine()).equals("quit")) {
                bufWriter.write(input); 
                 // newLine()方法写入与操作系统相依的换行字符
                bufWriter.newLine(); 
            } 
            bufReader.close();
            bufWriter.close(); 
    }
} 