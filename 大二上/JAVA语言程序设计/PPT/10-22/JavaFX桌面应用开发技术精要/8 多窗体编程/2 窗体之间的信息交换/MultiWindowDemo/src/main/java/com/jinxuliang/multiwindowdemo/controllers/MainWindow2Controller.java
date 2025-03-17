package com.jinxuliang.multiwindowdemo.controllers;

import com.jinxuliang.multiwindowdemo.MultiWindowApp;

public class MainWindow2Controller {
    private MultiWindowApp main;
    public void setMain(MultiWindowApp main) {
        this.main = main;
    }
    public void goBack() {
        main.showMainWindow();
    }

}
