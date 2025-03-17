public class InterruptSleepDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=()->{
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("被中断！");
                    break;
                }
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("休眠被打断");
                    //重置中断状态
                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}


