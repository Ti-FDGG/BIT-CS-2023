package threadpool;

public class Worker {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Worker(String name) {
        this.name = name;
    }
    public void process(String task) throws InterruptedException {
        System.out.println(name+"正在处理"+task);
        Thread.sleep(200);
        System.out.println(task+"处理完毕。");
    }
}
