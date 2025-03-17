package daytime;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//向客户端返回Server端的时间, 使用线程池

public class DayTimeServer3 {
    public final static int PORT = 8899;
    public static void main(String[] args) {
        //创建一个有50个线程对象的线程池
        ExecutorService pool = Executors.newFixedThreadPool(50);
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("正在监听端口" + PORT + ",等待客户端连接……");
            while (true) {
                Socket clientConnection = server.accept();
                //将完成数据传送工作的任务对象传给线程池去执行
                Callable<Void> task = new DateTimeTask(clientConnection);
                pool.submit(task);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //此类负责向客户端发送特定的数据
    private static class DateTimeTask implements Callable<Void> {
        private Socket clientConnection;
        public DateTimeTask(Socket connection) {
            this.clientConnection = connection;
        }
        @Override
        public Void call() {
            try {
                System.out.println("接收到客户端连接：" + clientConnection);
                System.out.println("负责处理的线程：" + Thread.currentThread());
                var out = new BufferedWriter(new OutputStreamWriter(
                        clientConnection.getOutputStream(),
                        StandardCharsets.UTF_8));
                Date now = new Date();
                String message = now.toString();
                out.write(message);
                out.newLine();
                out.flush();
                System.out.println("写入数据：" + message);

            } catch (Exception e) {

            } finally {
                try {
                    clientConnection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}

