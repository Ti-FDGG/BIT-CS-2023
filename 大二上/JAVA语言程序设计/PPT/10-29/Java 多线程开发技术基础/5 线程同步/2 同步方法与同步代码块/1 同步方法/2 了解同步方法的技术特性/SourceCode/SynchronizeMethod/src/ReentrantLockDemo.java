public class ReentrantLockDemo {
    public synchronized void a(){
        System.out.println("调用同步方法a");
        //继续调用另一个同步方法
        b();
    }
    public synchronized void b(){
        System.out.println("调用同步方法b");
    }

    public static void main(String[] args) {
        new ReentrantLockDemo().a();
    }
}

