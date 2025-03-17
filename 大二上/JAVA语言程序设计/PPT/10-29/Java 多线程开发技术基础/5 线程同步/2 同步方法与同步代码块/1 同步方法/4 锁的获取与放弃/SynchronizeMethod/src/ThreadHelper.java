//封装一些辅助代码以便重用
public class ThreadHelper {
    private final String name;
    public ThreadHelper(String name) {
        this.name = name;
    }
    public ThreadHelper(){
        this.name="线程" + Thread.currentThread().getName();
    }

    public void process() {
        for (int i = 0; i < 4; i++) {
            System.out.println(name + "正在处理" + i);
            try {
                //用于拖慢执行速度，以方便演示
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
