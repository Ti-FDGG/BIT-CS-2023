import java.util.concurrent.TimeUnit;

//此线程不断地查找质数
public class PrimeGenerator extends Thread {
    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.printf("%d 是质数。\n", number);
            }
            // 如果外界发出中断请求
            if (isInterrupted()) {
                System.out.println("质数生成过程被打断。");
                return;
            }
            number++;
        }
    }
    //判断一个数是不是质数
    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }
        for (long i = 2; i < number; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }
}

class TestPrimeGenerator {
    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start();
        //等待5秒之后，发出中断请求
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();
    }
}

