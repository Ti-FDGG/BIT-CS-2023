package com.jinxuliang.customeventtype;

import javafx.event.Event;
import javafx.event.EventType;

//自定义事件，携带两个信息：int和String
public class MyEvent extends Event {
    public MyEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public static final EventType<MyEvent> MY_EVENT =
            new EventType<>(ANY, "MY_EVENT");

    //region 事件携带的信息
    private int number = -1;
    private String info = "";
    //endregion

    //region getter & setter
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    //endregion
}
