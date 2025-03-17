package localdata;

import model.MyData;

public class MyThread extends Thread{
    private final MyData myData;
    public MyThread(MyData myData) {
        this.myData = myData;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myData.setValue(myData.getValue()+1);
    }
}
