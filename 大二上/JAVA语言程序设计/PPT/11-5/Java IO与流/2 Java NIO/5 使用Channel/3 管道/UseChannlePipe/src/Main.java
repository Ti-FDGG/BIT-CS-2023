import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        threadCommunicationViaChannelPipe();
    }
    //使用Channel构建线程间的管道
    private static void threadCommunicationViaChannelPipe()
            throws IOException {
        //构建管道
        Pipe pipe = Pipe.open();
        //数据发送方
        Runnable sender = () -> {
            Pipe.SinkChannel sinkChannel = pipe.sink();
            ByteBuffer buffer = ByteBuffer.allocate(256);
            for (int i = 0; i < 10; i++) {
                String currentTime = i + " 当前时间：" + System.currentTimeMillis();
                buffer.put(currentTime.getBytes(StandardCharsets.UTF_8));
                buffer.flip();
                while (buffer.hasRemaining()) {
                    try {
                        sinkChannel.write(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                buffer.flip();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //数据接收方
        Runnable receiver = () -> {
            Pipe.SourceChannel sourceChannel = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(256);
            for (int i = 0; i < 10; i++) {
                int bytesRead = 0;
                try {
                    bytesRead = sourceChannel.read(buffer);
                    byte[] timeStringData = new byte[bytesRead];
                    buffer.flip();
                    buffer.get(timeStringData);
                    String timeString = new String(timeStringData, StandardCharsets.UTF_8);
                    System.out.println("收到：" + timeString);
                    buffer.flip();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(sender).start();
        new Thread(receiver).start();
    }

}