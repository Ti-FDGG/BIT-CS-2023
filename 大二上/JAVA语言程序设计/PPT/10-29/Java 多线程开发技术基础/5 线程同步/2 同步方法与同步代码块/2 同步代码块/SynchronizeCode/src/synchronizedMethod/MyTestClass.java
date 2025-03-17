package synchronizedMethod;

public class MyTestClass  {
    private final String name;
    public MyTestClass(String name) {
        this.name = name;
    }
    public MyTestClass(){
        this.name="线程" + Thread.currentThread().getName();
    }
    public void process() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "正在处理" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
