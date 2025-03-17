package com.jinxuliang.javafxmvcdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PersonController {
    //获取关联FXML文档中指定Id的控件引用
    //变量名就是FXML文档中的控件id值
    @FXML private Label label;
    @FXML private TextField field;
    //此处定义的方法，可以在FXML文档中引用
    @FXML
    public void handleButtonClick() {
        String text = field.getText();
        label.setText(text);
        field.clear();
    }

    //控制器中，可以封装Model作为视图的数据源
    private Person person;
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    //可以将Application对象引入注入到控制器中，以便控制器
    //能访问其中定义的方法，完成特定的功能（比如切换窗体内容）
    private PersonApplication application;
    public void setApplication(PersonApplication app) {
        this.application = app;
        person = new Person("XuLiang", "jin", "45");
        label.setText(person.toString());
    }
}