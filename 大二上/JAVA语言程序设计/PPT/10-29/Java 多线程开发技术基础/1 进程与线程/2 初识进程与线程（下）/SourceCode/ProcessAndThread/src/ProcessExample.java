import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessExample {
    public static void main(String[] args) throws IOException {
//        getProcessInfo();
 //       startNotepad();
        getMyIPInfo();
    }


    private static void getMyIPInfo() {
        var processBuilder = new ProcessBuilder("ipconfig", "/all");
        try {
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "GBK"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startNotepad() throws IOException {
        ProcessBuilder builder =
                new ProcessBuilder("notepad", "sample.txt");
        builder.start();
    }

    private static void getProcessInfo() {
        System.out.println("列出当前所有的进程ID值:");
        ProcessHandle.allProcesses()
                .forEach(p -> System.out.print(p + ","));
        //获取当前进程的句柄
        var handle = ProcessHandle.current();
        System.out.println("当前进程ID：" + handle.pid());
        System.out.println("当前进程的信息：");
        System.out.println(handle.info());
        System.out.println("当前进程活跃的线程数：" + Thread.activeCount());
        //列举当前线程组中的所有线程
        Thread.currentThread().getThreadGroup().list();
    }
}
