package localdata;

public class MyThreadUseSynchronize extends Thread {
    private final MyData myData;
    public MyThreadUseSynchronize(MyData myData) {
        this.myData = myData;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用同步代码块，独占访问MyData
        synchronized (myData){
            myData.setValue(myData.getValue()+1);
        }
    }
}
